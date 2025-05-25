# Task-Productivity-App
Project Overview: Task Productivity Tracker or 
The Task Tracker is a Java-based desktop application designed to help users manage and organize their tasks efficiently. It follows an event-driven programming model and leverages core Object-Oriented Programming principles along with database integration.




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




**Technical Stack**
    
    Language: Java

    GUI Framework: AWT (Abstract Window Toolkit)

    Database: Mysql Workbench and Integrated via DAO classes 

    Architecture: Event-driven and modular design using OOP concepts




**Code Highlights**
    
    DashboardWindow manages the UI and interactions.

    TaskDAO handles data persistence and retrieval.

    Task and User are model classes used to encapsulate task/user details.
