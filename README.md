# Task-Productivity-App
Project Overview: Task Productivity Tracker or 
The Task Tracker is a Java-based desktop application designed to help users manage and organize their tasks efficiently. It follows an event-driven programming model and leverages core Object-Oriented Programming principles along with database integration.

SUBMITTED BY: 
ARNEL ENRICO PAOLO CODERA
KYLE GABRIEL GALANIDA
JHEMAR VISANDE


**Key Features**

    User Authentication and Registration
    Allows users to log in or create accounts to access a personalized task dashboard.

Login 



![image](https://github.com/user-attachments/assets/5519e4b3-9471-4342-a3db-313c9f1e1d53)

   
Registration



![image](https://github.com/user-attachments/assets/1e3b6439-b285-4d20-9e8d-ee700c78c319)

    


    Task Management

    Add new tasks with title, description, due date, and status.

![image](https://github.com/user-attachments/assets/8abad909-004c-40ac-bd5b-60a2d54fcfc4)


    Mark tasks as "Pending" or "Completed".

![image](https://github.com/user-attachments/assets/9a0c14cb-2abe-4e46-bf9d-e8b7dbb3f752)

    Delete tasks with confirmation.

![image](https://github.com/user-attachments/assets/4e5370ab-504e-45f4-af8c-7f9c16eeadad)

    Task Filtering
    Users can filter the task list by "Pending", "Completed", or "All".

![image](https://github.com/user-attachments/assets/2fe0b2c8-5583-4a65-9005-1128ce096486)

    Task Reminder
    Displays a popup reminder upon login if the user has any pending tasks.

![image](https://github.com/user-attachments/assets/345d50c0-8449-4fe0-a537-114d394078e5)


    Dynamic UI Updates
    The interface automatically updates after any changes to tasks (e.g., add, delete, update).

![image](https://github.com/user-attachments/assets/08b0e329-4737-4d6f-b60d-de0f50d18d22)

    Due Date Display
    The task list shows both the title and the due date of each task.

![image](https://github.com/user-attachments/assets/16812369-a76c-4878-ae75-c548a6a5b9c4)

**Database**
    
    Table for users
    where Login/Registered Credentials are stored and taken during login
    Password Hashing is Utilized as Username Unique Modified is also enabled
![image](https://github.com/user-attachments/assets/e55c07ac-0b83-42ec-bbb6-13651f059c3c)

    Table for Tasks 
    where tasks created are stored and where the application grenerates the dashboad 
![image](https://github.com/user-attachments/assets/a11de36c-e23d-400e-b25a-664431ca47f7)
    

**Technical Stack**
    
    Language: Java

    GUI Framework: AWT (Abstract Window Toolkit)

    Database: Mysql Workbench and Integrated via DAO classes 

    Architecture: Event-driven and modular design using OOP concepts


**Code Highlights**
    
    DashboardWindow manages the UI and interactions.

    TaskDAO handles data persistence and retrieval.

    Task and User are model classes used to encapsulate task/user details.

**How to Run the Task Productivity App**
    
    This guide assumes you are using Java (JDK 8 or later) and a MySQL database. Follow the steps below to set up and run the application.

**Prerequisites**

    Java JDK 8+ installed

    MySQL Server running

    IDE like IntelliJ, Eclipse, or NetBeans (optional)

    MySQL JDBC driver included in your project classpath


**Step 1: Create The Databases**        
    
    CREATE DATABASE task_tracker_db;
    USE task_tracker_db;
    
**Step 2:Import tables and data**

    mysql -u your_username -p task_tracker_db < path/to/task_tracker_db_users.sql
    mysql -u your_username -p task_tracker_db < path/to/task_tracker_db_tasks.sql

**Step 3:Configure Database in Your App**
    
    private static final String URL = "jdbc:mysql://localhost:3306/task_tracker_db";
    private static final String USER = "your_mysql_user";
    private static final String PASSWORD = "your_mysql_password";
    
**Step 4: Compile and Run the App**

    import the Java project folder.
    compile the application and run the App.java to start the application
