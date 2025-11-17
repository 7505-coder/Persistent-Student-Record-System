package app;

import model.Student;
import service.StudentManager;
import exceptions.StudentNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String path = "data/students.txt";
        StudentManager mgr = new StudentManager(path);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");
            String chs = sc.nextLine().trim();
            try {
                int ch = Integer.parseInt(chs);
                switch (ch) {
                    case 1:
                        Student s = new Student();
                        s.inputDetails(sc);
                        boolean added = mgr.addStudent(s);
                        System.out.println(added ? "Added." : "Not added.");
                        break;
                    case 2:
                        List<Student> list = mgr.viewAllStudents();
                        for (Student st : list) { st.displayInfo(); System.out.println("----------------"); }
                        break;
                    case 3:
                        System.out.print("Enter name to search: "); String name = sc.nextLine();
                        try {
                            Student found = mgr.searchStudent(name);
                            found.displayInfo();
                        } catch (StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter name to delete: "); String dname = sc.nextLine();
                        try {
                            boolean del = mgr.deleteStudent(dname);
                            System.out.println("Deleted: " + del);
                        } catch (StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        mgr.sortByMarksDesc();
                        System.out.println("Sorted by marks (desc):");
                        for (Student st : mgr.viewAllStudents()) { st.displayInfo(); System.out.println(); }
                        break;
                    case 6:
                        mgr.save();
                        System.out.println("Saved and exiting.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter numeric choice.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
