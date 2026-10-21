import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager(sc);

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Enrol Student");
            System.out.println("2. Grade Student");
            System.out.println("3. Search Student");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    manager.enrolStudent();
                    break;

                case "2":
                    manager.gradeStudent();
                    break;

                case "3":
                    manager.searchStudent();
                    break;

                case "4":
                    manager.generateReport();
                    break;

                case "5":
                    System.out.println("Thank you for using the system.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}