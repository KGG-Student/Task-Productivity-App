package com.tasktracker.gui;

import com.tasktracker.dao.UserDAO;
import com.tasktracker.model.User;

import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends Frame implements ActionListener {
    Label lblUsername, lblPassword, lblStatus;
    TextField tfUsername, tfPassword;
    Button btnLogin, btnRegister;

    public LoginWindow() {
        // UI components
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblStatus = new Label("");

        tfUsername = new TextField();
        tfPassword = new TextField();
        tfPassword.setEchoChar('*');

        btnLogin = new Button("Login");
        btnLogin.addActionListener(this);

        btnRegister = new Button("Register");
        btnRegister.addActionListener(e -> {
            dispose();
            new RegisterWindow();
        });

        // Layout
        setLayout(null);
        lblUsername.setBounds(50, 50, 80, 25);
        tfUsername.setBounds(140, 50, 150, 25);

        lblPassword.setBounds(50, 90, 80, 25);
        tfPassword.setBounds(140, 90, 150, 25);

        btnLogin.setBounds(140, 130, 80, 30);
        btnRegister.setBounds(230, 130, 80, 30);
        lblStatus.setBounds(50, 170, 260, 25);

        add(lblUsername);
        add(tfUsername);
        add(lblPassword);
        add(tfPassword);
        add(btnLogin);
        add(btnRegister);
        add(lblStatus);

        setTitle("Task Tracker - Login");
        setSize(370, 250);
        setVisible(true);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        UserDAO userDAO = new UserDAO();
        if (userDAO.validateLogin(username, password)) {
            lblStatus.setText("✅ Login successful!");
            lblStatus.setForeground(Color.GREEN);
            User user = userDAO.getUserByUsername(username);
            dispose();
            new DashboardWindow(user); 
        } else {
            lblStatus.setText("❌ Invalid credentials");
            lblStatus.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}
