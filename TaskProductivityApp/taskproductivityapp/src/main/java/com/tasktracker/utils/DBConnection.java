package com.tasktracker.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/task_tracker_db";
    private static final String USER = "root"; // change if your username is different
    private static final String PASSWORD = "12345"; // add your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
