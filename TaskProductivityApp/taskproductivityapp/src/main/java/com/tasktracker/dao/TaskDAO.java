package com.tasktracker.dao;

import com.tasktracker.model.Task;
import com.tasktracker.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // Create a new task, retrieve and set generated ID
    public boolean createTask(Task task) {
        String sql = "INSERT INTO tasks (user_id, title, description, due_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, task.getUserId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setDate(4, Date.valueOf(task.getDueDate()));
            stmt.setString(5, task.getStatus());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        task.setId(keys.getInt(1));
                    }
                }
            }
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error creating task: " + e.getMessage());
            return false;
        }
    }

    // Retrieve tasks with proper ID mapping
    public List<Task> getTasksByUser(int userId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT id, user_id, title, description, due_date, status FROM tasks WHERE user_id = ? ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task t = new Task(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("status")
                    );
                    tasks.add(t);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }
        return tasks;
    }

    // Update existing task
    public boolean updateTask(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getDueDate()));
            stmt.setString(4, task.getStatus());
            stmt.setInt(5, task.getId());

            int rows = stmt.executeUpdate();
            System.out.println("updateTask - rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
            return false;
        }
    }

    // Delete a task by ID
    public boolean deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, taskId);
            int rows = stmt.executeUpdate();
            System.out.println("deleteTask - rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
            return false;
        }
    }
}
