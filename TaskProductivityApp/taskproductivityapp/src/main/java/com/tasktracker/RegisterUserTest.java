package com.tasktracker;

import com.tasktracker.dao.UserDAO;
import com.tasktracker.model.User;
import com.tasktracker.utils.PasswordUtils;

import java.util.Scanner;

public class RegisterUserTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Hash password
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Create user and save
        User newUser = new User(username, hashedPassword);
        UserDAO userDAO = new UserDAO();

        if (userDAO.createUser(newUser)) {
            System.out.println("✅ User registered successfully!");
        } else {
            System.out.println("❌ Failed to register user.");
        }

        scanner.close();
    }
}
