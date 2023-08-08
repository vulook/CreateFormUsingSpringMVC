# CreateFormUsingSpringMVC
## Project


The *CreateFormUsingSpringMVC* project is a web application developed using the Spring MVC framework that allows users to save and manage a list of students. The main goal of this project is to learn the basics of working with Spring Framework and implement basic CRUD (Create, Read, Update, Delete) functionality for students.


**Project structure**

The project is split into different packages and classes for better organization and code maintenance. 

*The main components of the project are:*

**model:** A package that contains the Student class, which represents a student model with the basic attributes, such as name, surname, email, phone, and date of birth.

**repository:** A package that contains the StudentRepository interface and its implementation StudentRepositoryImpl. This is where the interaction with the list of students takes place, including saving, updating, deleting, and retrieving data.

**controller:** A package that contains the StudentController controller, which is responsible for processing user requests. The controller defines the URL paths and methods for adding, updating, deleting, and displaying students.

**util:** A package that contains the StudentUtil utility class, which helps to read and save student data in JSON format.



### Implementation

The project provides users with the following functionality:

*View a list of students:*
Users can view a list of all students, including their name, surname, and age.

*View student details:*
Users can view the details of a specific student, including all of their attributes.

*Add a new student:*
Users can add a new student by specifying their name, surname, email, phone, and date of birth.

*Update student information:*
Users can update the data of an existing student by changing any of the attributes.

*Delete a student:*
Users can delete an existing student from the list.

*Display the number of records:*
The project supports displaying the total number of students in the system.


### Steps to work with the project

***Project creation:***
First, the CreateFormUsingSpringMVC project was created, in which the necessary packages for the model, repository, controller, and utilities were organized.

***Student model:***
The Student class was defined, which contains the student attributes, such as name, surname, email, phone, and date of birth.

***Repository and utilities:***
The StudentRepository interface and its implementation StudentRepositoryImpl were implemented. These are responsible for storing and processing student data. The StudentUtil class was also implemented, which helps to read and save data in JSON format.

***Student controller:***
The StudentController controller was created, which defines the URL paths and methods for processing user requests. The controller allows to display a list, add, update, and delete students.

***Web forms:*** 
Web forms were created for interaction with the user. They allow users to view a list of students, add a new student, update, and delete an existing one.

***Date processing:**
A method for processing the date on the controller side was implemented. It converts the date string from the form to a LocalDate object.

***Duplicate handling:***
A check for duplicate student names, surnames, and emails was performed when adding and updating.

***Displaying results:***
Displaying the number of records and messages about successful user actions was implemented.

---

#### The CreateFormUsingSpringMVC project demonstrates how to apply the basics of Spring MVC for developing web applications with basic create, read, update, and delete data functionality.