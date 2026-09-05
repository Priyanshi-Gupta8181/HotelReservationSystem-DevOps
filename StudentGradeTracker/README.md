# Student Grade Tracker

A GUI-based Java application for managing student grades and generating a summary report. The application is developed using Java Swing and uses ArrayList and file storage to store and restore student records.

## Features

- Add student details
- Store student name and roll number
- Select assessment type: Percentage or CGPA
- Enter Percentage in the range 0 to 100
- Enter CGPA in the range 0 to 10
- Automatically convert Percentage to CGPA
- Automatically convert CGPA to Percentage
- Calculate average percentage
- Calculate average CGPA
- Find highest percentage
- Find lowest percentage
- Automatically calculate letter grades
- Display all students in a table
- Delete selected student
- View complete student report
- Input validation for Percentage and CGPA
- Automatically save student data
- Automatically restore previous student data after restarting
- User-friendly GUI interface

## Technologies Used

- Java
- Java Swing
- ArrayList
- File Handling
- Serialization
- JTable
- JComboBox
- Object-Oriented Programming (OOP)

## Project Structure

StudentGradeTracker/

│

├── README.md

├── students.dat

│

└── src/

    ├── Student.java

    ├── GradeManager.java

    └── StudentGradeTrackerGUI.java

## File Description

### Student.java

This class represents a student and stores:

- Student Name
- Roll Number
- Marks
- Assessment Type

It also calculates the student's letter grade and performs Percentage and CGPA conversion.

### GradeManager.java

This class manages all student records using ArrayList.

It performs:

- Adding students
- Calculating average percentage
- Calculating average CGPA
- Finding highest percentage
- Finding lowest percentage
- Finding highest CGPA
- Finding lowest CGPA
- Saving student data
- Loading previous student data
- Managing student records

### StudentGradeTrackerGUI.java

This is the main GUI class of the project.

It provides:

- Input fields
- Assessment type selection
- Percentage/CGPA input
- Buttons
- Student table
- Grade summary
- Student report
- Delete functionality
- Input validation
- Automatic loading of saved student records

### students.dat

This file stores the student records permanently.

The data is automatically saved when a student is added or deleted and automatically loaded when the application is started again.

## Requirements

- Java JDK 8 or later
- Visual Studio Code or any Java IDE
- Java Extension Pack for VS Code

No external libraries are required.

## How to Run

### Using VS Code

Open and run `StudentGradeTrackerGUI.java`.

## How to Use

1. Enter the Student Name.
2. Enter the Roll Number.
3. Select Percentage or CGPA.
4. Enter Percentage in the range 0 to 100 or CGPA in the range 0 to 10.
5. Click Add Student.
6. The student will be added to the table and saved automatically.
7. Click Calculate Summary to view the total students, average percentage, average CGPA, highest percentage, and lowest percentage.
8. Select a student from the table and click Delete Selected to remove the student.
9. Click View Report to generate a complete student grade report.
10. Click Clear Fields to clear the input fields.
11. Click Exit to close the application.
12. When the application is run again, previously saved student records will be restored automatically.

## CGPA and Percentage Conversion

Percentage = CGPA × 9.5

CGPA = Percentage ÷ 9.5

## Grade Calculation

| Percentage | Grade |
|-------|-------|
| 90 - 100 | A+ |
| 80 - 89 | A |
| 70 - 79 | B |
| 60 - 69 | C |
| 50 - 59 | D |
| Below 50 | F |

## Concepts Used

- Classes and Objects
- Encapsulation
- Constructors
- Methods
- ArrayList
- File Handling
- Serialization
- Loops
- Conditional Statements
- Exception Handling
- Java Swing
- Event Handling
- JTable
- JComboBox
- Input Validation
- Object-Oriented Programming

## Project Objective

The main objective of this project is to develop a simple GUI-based Student Grade Tracker that allows users to efficiently manage student grades, store student records permanently, and generate useful statistical information such as average, highest, and lowest scores.

## Future Enhancements

- Add database connectivity
- Add search student functionality
- Add edit/update student functionality
- Export reports to PDF or Excel
- Add login authentication
- Add graphical charts for grade analysis

## Author

Priyanshi Gupta