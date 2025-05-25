package com.tasktracker.gui;

import com.tasktracker.dao.TaskDAO;
import com.tasktracker.model.Task;
import com.tasktracker.model.User;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class DashboardWindow extends Frame {
    private User user;
    private TaskDAO taskDAO = new TaskDAO();
    private List<Task> taskList;
    private java.awt.List taskListComponent;
    private TextArea descriptionArea;
    private int selectedTaskIndex = -1;

    private Choice filterChoice = new Choice();

    public DashboardWindow(User user) {
        this.user = user;
        setTitle("Dashboard - " + user.getUsername());
        setSize(600, 500);
        setLayout(new BorderLayout());

        taskListComponent = new java.awt.List();
        descriptionArea = new TextArea("", 3, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
        descriptionArea.setEditable(false);

        add(taskListComponent, BorderLayout.CENTER);
        add(descriptionArea, BorderLayout.SOUTH);

        taskListComponent.addItemListener(e -> {
            selectedTaskIndex = taskListComponent.getSelectedIndex();
            if (selectedTaskIndex >= 0 && selectedTaskIndex < taskList.size()) {
                Task selectedTask = taskList.get(selectedTaskIndex);
                descriptionArea.setText("Description: " + selectedTask.getDescription());
            } else {
                descriptionArea.setText("");
            }
        });

        Panel buttonPanel = new Panel();

        Button addBtn = new Button("Add Task");
        Button delBtn = new Button("Delete Task");
        Button completeBtn = new Button("Mark Complete");
        Button pendingBtn = new Button("Mark Pending");
        Button logoutBtn = new Button("Logout");

        filterChoice.add("Pending");
        filterChoice.add("Completed");
        filterChoice.add("All");
        filterChoice.addItemListener(e -> loadTasks(filterChoice.getSelectedItem()));

        buttonPanel.add(new Label("Filter:"));
        buttonPanel.add(filterChoice);
        buttonPanel.add(addBtn);
        buttonPanel.add(delBtn);
        buttonPanel.add(completeBtn);
        buttonPanel.add(pendingBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.NORTH);

        loadTasks(filterChoice.getSelectedItem());

        addBtn.addActionListener(e -> showAddTaskDialog());

        delBtn.addActionListener(e -> {
    if (selectedTaskIndex >= 0 && selectedTaskIndex < taskList.size()) {
        Task t = taskList.get(selectedTaskIndex);

        Dialog confirm = new Dialog(this, "Confirm Delete", true);
        confirm.setLayout(new FlowLayout());
        confirm.add(new Label("Are you sure you want to delete this task?"));

        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        confirm.add(yesBtn);
        confirm.add(noBtn);

        yesBtn.addActionListener(evt -> {
            taskDAO.deleteTask(t.getId());
            confirm.dispose();
            selectedTaskIndex = -1;
            loadTasks(filterChoice.getSelectedItem());
            descriptionArea.setText("");
        });

        noBtn.addActionListener(evt -> confirm.dispose());

        confirm.setSize(250, 100);
        confirm.setLocationRelativeTo(this);
        confirm.setVisible(true);
    }
});


        completeBtn.addActionListener(e -> {
            if (selectedTaskIndex >= 0 && selectedTaskIndex < taskList.size()) {
                Task t = taskList.get(selectedTaskIndex);
                t.setStatus("Completed");
                taskDAO.updateTask(t);
                selectedTaskIndex = -1;
                loadTasks(filterChoice.getSelectedItem());
                descriptionArea.setText("");
            }
        });

        pendingBtn.addActionListener(e -> {
            if (selectedTaskIndex >= 0 && selectedTaskIndex < taskList.size()) {
                Task t = taskList.get(selectedTaskIndex);
                t.setStatus("Pending");
                taskDAO.updateTask(t);
                selectedTaskIndex = -1;
                loadTasks(filterChoice.getSelectedItem());
                descriptionArea.setText("");
            }
        });

        logoutBtn.addActionListener(e -> {
    Dialog confirm = new Dialog(this, "Confirm Logout", true);
    confirm.setLayout(new FlowLayout());
    confirm.add(new Label("Are you sure you want to log out?"));

    Button yesBtn = new Button("Yes");
    Button noBtn = new Button("No");
    confirm.add(yesBtn);
    confirm.add(noBtn);

    yesBtn.addActionListener(evt -> {
        confirm.dispose();
        dispose();
        new LoginWindow();
    });

    noBtn.addActionListener(evt -> confirm.dispose());

    confirm.setSize(210, 100);
    confirm.setLocationRelativeTo(this);
    confirm.setVisible(true);
});

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        showTaskReminder();
        setVisible(true);
    }

    private void loadTasks(String statusFilter) {
    taskListComponent.removeAll();
    descriptionArea.setText("");
    selectedTaskIndex = -1;

    taskList = taskDAO.getTasksByUser(user.getId()).stream()
        .filter(task -> statusFilter.equals("All") || task.getStatus().equalsIgnoreCase(statusFilter))
        .toList();

    for (Task t : taskList) {
        taskListComponent.add(t.getTitle() + " | " + t.getStatus() + " | Due: " + t.getDueDate());
    }
}

    private void showTaskReminder() {
    if (taskList == null || taskList.isEmpty()) return;

    long pendingCount = taskList.stream()
        .filter(task -> "Pending".equalsIgnoreCase(task.getStatus()))
        .count();

    if (pendingCount > 0) {
        Dialog reminderDialog = new Dialog(this, "Task Reminder", true);
        reminderDialog.setLayout(new FlowLayout());
        reminderDialog.add(new Label("You have " + pendingCount + " pending task(s)."));

        Button okBtn = new Button("OK");
        okBtn.addActionListener(e -> reminderDialog.dispose());
        reminderDialog.add(okBtn);

        reminderDialog.setSize(250, 100);
        reminderDialog.setLocationRelativeTo(this);
        reminderDialog.setVisible(true);
    }
}

    private void showAddTaskDialog() {
        Dialog dialog = new Dialog(this, "Add Task", true);
        dialog.setLayout(new GridLayout(6, 2));

        TextField titleField = new TextField();
        TextField descField = new TextField();
        TextField dueDateField = new TextField();
        Choice statusChoice = new Choice();
        statusChoice.add("Pending");
        statusChoice.add("Completed");

        dialog.add(new Label("Title:"));
        dialog.add(titleField);
        dialog.add(new Label("Description:"));
        dialog.add(descField);
        dialog.add(new Label("Due Date (YYYY-MM-DD):"));
        dialog.add(dueDateField);
        dialog.add(new Label("Status:"));
        dialog.add(statusChoice);

        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");
        dialog.add(saveBtn);
        dialog.add(cancelBtn);

        saveBtn.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();
                LocalDate dueDate = LocalDate.parse(dueDateField.getText().trim());
                String status = statusChoice.getSelectedItem();

                Task task = new Task(user.getId(), title, desc, dueDate, status);
                taskDAO.createTask(task);
                dialog.dispose();
                loadTasks(filterChoice.getSelectedItem());  
            } catch (Exception ex) {
                Dialog err = new Dialog(dialog, "Error", true);
                err.setLayout(new FlowLayout());
                err.add(new Label("Invalid date format or input"));
                Button ok = new Button("OK");
                ok.addActionListener(a -> err.dispose());
                err.add(ok);
                err.setSize(250, 100);
                err.setVisible(true);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setSize(350, 300);
        dialog.setLocation(200, 200);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardWindow(new User(1, "testuser", ""));
    }
}
