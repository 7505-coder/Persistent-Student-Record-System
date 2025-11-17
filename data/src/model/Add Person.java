package model;

public class Student extends Person {
    public Integer rollNo;
    public String course;
    public Double marks;
    public char grade;

    public Student() {}

    public Student(Integer rollNo, String name, String email, String course, Double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    public void inputDetails(java.util.Scanner sc) {
        System.out.print("Enter Roll No: "); this.rollNo = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Name: "); this.name = sc.nextLine();
        System.out.print("Enter Email: "); this.email = sc.nextLine();
        System.out.print("Enter Course: "); this.course = sc.nextLine();
        System.out.print("Enter Marks: "); this.marks = Double.parseDouble(sc.nextLine());
        calculateGrade();
    }

    public void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 50) grade = 'C';
        else grade = 'D';
    }

    @Override
    public void displayInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }

    @Override
    public String toString() {
        return rollNo + "," + name + "," + email + "," + course + "," + marks;
    }
}
