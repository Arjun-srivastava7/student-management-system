
# Student Management System

A menu-driven Student Management System built using Java.
This project demonstrates object-oriented programming,
collections, exception handling, and file handling.

## Features

- Enrol new students
- Assign and update student grades
- Search students by ID
- Generate a report of all students
- Save and load student records using a text file
- Prevent duplicate student IDs

## Class Hierarchy

- StudentOperations: Interface defining the required operations.
- Student: Stores a student's ID, name, and grade.
- StudentManager: Implements StudentOperations and manages
  student records, reports, and file handling.
- Main: Runs the menu-driven application.

## Requirements

- Java JDK 17 or later

## How to Run

1. Download or clone this repository.
2. Open a terminal in the project folder.
3. Compile the Java files:

   javac StudentOperations.java Student.java StudentManager.java Main.java

4. Run the application:

   java Main

## Data Storage

Student records are saved in students.txt.
The program loads saved records when it starts.

## Concepts Used

- Interfaces
- Classes and objects
- Encapsulation
- ArrayList
- Loops and switch statements
- Exception handling
- File handling
