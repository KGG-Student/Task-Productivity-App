package com.tasktracker.dao;

import com.tasktracker.model.User;
import com.tasktracker.utils.DBConnection;
import com.tasktracker.utils.PasswordUtils;

import java.sql.*;

public class UserDAO {

    /** 
     * Create new user (Register).
     * Takes raw password, hashes it, then stores.
     */
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            // Hash here so caller doesn’t need to
            stmt.setString(2, PasswordUtils.hashPassword(user.getPasswordHash()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: Username already exists.");
            return false;
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetch a user by username.
     * Returns a User object (with id, username, and hashed password) or null if not found.
     */
    public User getUserByUsername(String username) {
        String sql = "SELECT id, username, password_hash FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    /**
     * Validate login credentials.
     * Hashes the input password and compares to stored hash.
     */
    public boolean validateLogin(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {
            return false; // user not found
        }

        String inputHash = PasswordUtils.hashPassword(password);
        return inputHash.equals(user.getPasswordHash());
    }
}
