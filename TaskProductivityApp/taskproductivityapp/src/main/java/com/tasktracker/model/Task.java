package com.tasktracker.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private int userId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status;

    // Default constructor
    public Task() {}

    // Constructor without ID (used for creating new tasks before DB assigns ID)
    public Task(int userId, String title, String description, LocalDate dueDate, String status) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Constructor with ID (used when retrieving from database)
    public Task(int id, int userId, String title, String description, LocalDate dueDate, String status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
