import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager implements StudentOperations {

    private ArrayList<Student> students = new ArrayList<>();
    private Scanner sc;
    private final String FILE_NAME = "students.txt";

    public StudentManager(Scanner sc) {
        this.sc = sc;
        loadStudents();
    }

    // Add a new student
    @Override
    public void enrolStudent() {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            if (findStudent(id) != null) {
                System.out.println("Student ID already exists.");
                return;
            }

            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty() || name.contains("|")) {
                System.out.println("Enter a valid name.");
                return;
            }

            Student student = new Student(id, name, "Not graded");
            students.add(student);
            saveStudents();

            System.out.println("Student enrolled successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Student ID must be a number.");
        }
    }

    // Assign or update a grade
    @Override
    public void gradeStudent() {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            Student student = findStudent(id);

            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("Enter grade: ");
            String grade = sc.nextLine().trim();

            if (grade.isEmpty() || grade.contains("|")) {
                System.out.println("Enter a valid grade.");
                return;
            }

            student.setGrade(grade);
            saveStudents();

            System.out.println("Grade updated successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Student ID must be a number.");
        }
    }

    // Find a student by ID
    @Override
    public void searchStudent() {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            Student student = findStudent(id);

            if (student == null) {
                System.out.println("Student not found.");
            } else {
                displayStudent(student);
            }

        } catch (NumberFormatException e) {
            System.out.println("Student ID must be a number.");
        }
    }

    // Display all students
    @Override
    public void generateReport() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n--- Student Report ---");
        System.out.printf("%-10s %-20s %-15s%n",
                "ID", "Name", "Grade");

        for (Student student : students) {
            System.out.printf("%-10d %-20s %-15s%n",
                    student.getId(),
                    student.getName(),
                    student.getGrade());
        }

        System.out.println("Total students: " + students.size());
    }

    // Search the list internally
    private Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Display one student
    private void displayStudent(Student student) {
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Grade: " + student.getGrade());
    }

    // Save records to the file
    private void saveStudents() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving student records.");
        }
    }

    // Load records when the program starts
    private void loadStudents() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                if (data.length == 3) {
                    try {
                        int id = Integer.parseInt(data[0]);

                        if (findStudent(id) == null
                                && !data[1].isEmpty()
                                && !data[1].contains("|")
                                && !data[2].isEmpty()
                                && !data[2].contains("|")) {
                            students.add(
                                new Student(id, data[1], data[2])
                            );
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(
                            "Skipping an invalid student record."
                        );
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading student records.");
        }
    }
}