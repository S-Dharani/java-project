package com.project;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    int age;
    String department;

    public Student(int id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name +
                           ", Age: " + age + ", Department: " + department);
    }
}

public class StudentRecordManager {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== Student Record Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    public static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter department: ");
        String dept = scanner.nextLine();

        Student student = new Student(idCounter++, name, age, dept);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.displayInfo();
        }
    }

    public static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Student s : students) {
            if (s.id == id) {
                found = true;
                System.out.print("Enter new name: ");
                s.name = scanner.nextLine();
                System.out.print("Enter new age: ");
                s.age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new department: ");
                s.department = scanner.nextLine();
                System.out.println("Student updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = students.removeIf(s -> s.id == id);

        if (removed) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
}
