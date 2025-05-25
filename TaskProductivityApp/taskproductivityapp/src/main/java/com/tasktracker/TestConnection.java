package com.tasktracker;

import com.tasktracker.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect: " + e.getMessage());
        }
    }
}
