package com.tasktracker.dao;

import com.tasktracker.model.User;
import com.tasktracker.utils.DBConnection;
import com.tasktracker.utils.PasswordUtils;

import java.sql.*;

public class UserDAO {

   
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String username = user.getUsername().trim();
            String rawPassword = user.getPasswordHash().trim();
            String hashedPassword = PasswordUtils.hashPassword(rawPassword);

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered: " + username + " (hash: " + hashedPassword + ")");
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: Username already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
        return false;
    }

   
    public User getUserByUsername(String username) {
        String sql = "SELECT id, username, password_hash FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password_hash")
                    );
                    System.out.println("Fetched user: " + user.getUsername() + " (hash: " + user.getPasswordHash() + ")");
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    
    public boolean validateLogin(String username, String password) {
        String userInput = username.trim();
        String rawPassword = password.trim();
        User user = getUserByUsername(userInput);
        if (user == null) {
            System.err.println("Login failed: user not found for username '" + userInput + "'");
            return false;
        }

        String inputHash = PasswordUtils.hashPassword(rawPassword);
        System.out.println("Comparing hashes -> Input: " + inputHash + " Stored: " + user.getPasswordHash());
        boolean valid = inputHash.equals(user.getPasswordHash());
        if (!valid) {
            System.err.println("Login failed: password mismatch for user '" + userInput + "'");
        }
        return valid;
    }
}
