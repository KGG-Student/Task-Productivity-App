package com.tasktracker.gui;

import com.tasktracker.dao.TaskDAO;
import com.tasktracker.model.Task;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class AddTaskDialog extends Dialog implements ActionListener {
    TextField tfTitle;
    TextArea taDescription;
    Button btnSave, btnCancel;
    Runnable refreshCallback;
    int userId;

    public AddTaskDialog(Frame parent, int userId, Runnable refreshCallback) {
        super(parent, "Add New Task", true);
        this.userId = userId;
        this.refreshCallback = refreshCallback;

        setLayout(null);
        setSize(350, 300);
        setLocationRelativeTo(parent);

        Label lblTitle = new Label("Title:");
        lblTitle.setBounds(20, 40, 50, 20);
        tfTitle = new TextField();
        tfTitle.setBounds(80, 40, 240, 25);

        Label lblDesc = new Label("Description:");
        lblDesc.setBounds(20, 80, 70, 20);
        taDescription = new TextArea();
        taDescription.setBounds(20, 110, 300, 80);

        btnSave = new Button("Save");
        btnSave.setBounds(60, 210, 80, 30);
        btnSave.addActionListener(this);

        btnCancel = new Button("Cancel");
        btnCancel.setBounds(180, 210, 80, 30);
        btnCancel.addActionListener(e -> dispose());

        add(lblTitle);
        add(tfTitle);
        add(lblDesc);
        add(taDescription);
        add(btnSave);
        add(btnCancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = tfTitle.getText().trim();
        String desc = taDescription.getText().trim();

        if (title.isEmpty()) {
            Dialog alert = new Dialog(this, "Error", true);
            alert.setLayout(new FlowLayout());
            alert.add(new Label("Title cannot be empty."));
            Button ok = new Button("OK");
            ok.addActionListener(a -> alert.dispose());
            alert.add(ok);
            alert.setSize(200, 100);
            alert.setLocationRelativeTo(this);
            alert.setVisible(true);
            return;
        }

        Task task = new Task(
            userId,
            title,
            desc,
            LocalDate.now().plusDays(1),
            "Pending"
        );

        TaskDAO dao = new TaskDAO();
        dao.createTask(task);

        dispose();
        refreshCallback.run();
    }
}
