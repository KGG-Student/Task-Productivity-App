# Task-Productivity-App
Project Overview: Task Productivity Tracker or 
The Task Tracker is a Java-based desktop application designed to help users manage and organize their tasks efficiently. It follows an event-driven programming model and leverages core Object-Oriented Programming principles along with database integration.




**Key Features**

    User Authentication
    Allows users to log in to access their personalized task dashboard.



    ![image](https://github.com/user-attachments/assets/fecd33dd-47fb-4c47-9ae9-d37acd534d81)
    


    Task Management

    Add new tasks with title, description, due date, and status.

    Mark tasks as "Pending" or "Completed".

    Delete tasks with confirmation.

    Task Filtering
    Users can filter the task list by "Pending", "Completed", or "All".

    Task Reminder
    Displays a popup reminder upon login if the user has any pending tasks.

    Dynamic UI Updates
    The interface automatically updates after any changes to tasks (e.g., add, delete, update).

    Due Date Display
    The task list shows both the title and the due date of each task.




**Technical Stack**
    
    Language: Java

    GUI Framework: AWT (Abstract Window Toolkit)

    Database: Mysql Workbench and Integrated via DAO classes 

    Architecture: Event-driven and modular design using OOP concepts




**Code Highlights**
    
    DashboardWindow manages the UI and interactions.

    TaskDAO handles data persistence and retrieval.

    Task and User are model classes used to encapsulate task/user details.
