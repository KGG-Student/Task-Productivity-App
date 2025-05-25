package com.tasktracker.gui;

import com.tasktracker.dao.UserDAO;
import com.tasktracker.model.User;

import java.awt.*;
import java.awt.event.*;

public class RegisterWindow extends Frame implements ActionListener {
    Label lblUsername, lblPassword, lblStatus;
    TextField tfUsername, tfPassword;
    Button btnRegister, btnBack;

    public RegisterWindow() {
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblStatus = new Label("");

        tfUsername = new TextField();
        tfPassword = new TextField();
        tfPassword.setEchoChar('*');

        btnRegister = new Button("Register");
        btnRegister.addActionListener(this);

        btnBack = new Button("Back");
        btnBack.addActionListener(e -> {
            dispose();
            new LoginWindow();
        });

        setLayout(null);
        lblUsername.setBounds(50, 50, 80, 25);
        tfUsername.setBounds(140, 50, 150, 25);

        lblPassword.setBounds(50, 90, 80, 25);
        tfPassword.setBounds(140, 90, 150, 25);

        btnRegister.setBounds(140, 130, 80, 30);
        btnBack.setBounds(230, 130, 80, 30);
        lblStatus.setBounds(50, 170, 260, 25);

        add(lblUsername);
        add(tfUsername);
        add(lblPassword);
        add(tfPassword);
        add(btnRegister);
        add(btnBack);
        add(lblStatus);

        setTitle("Register New User");
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
        String username = tfUsername.getText().trim();
        String password = tfPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            lblStatus.setText("⚠️ Fields cannot be empty.");
            lblStatus.setForeground(Color.RED);
            return;
        }

        UserDAO dao = new UserDAO();
        boolean success = dao.createUser(new User(username, password));

        if (success) {
    lblStatus.setText("Registered successfully!");
    lblStatus.setForeground(Color.GREEN);


    } else {
    lblStatus.setText("Registration failed. Try another username.");
    lblStatus.setForeground(Color.RED);
    }
    
}

    public static void main(String[] args) {
        new RegisterWindow();
    }
}
