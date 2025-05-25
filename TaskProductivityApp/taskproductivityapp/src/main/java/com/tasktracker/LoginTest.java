package com.tasktracker;

import com.tasktracker.dao.UserDAO;

import java.util.Scanner;

public class LoginTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (userDAO.validateLogin(username, password)) {
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Invalid username or password.");
        }

        scanner.close();
    }
}
