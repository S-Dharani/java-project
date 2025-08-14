package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class EmployeeDataBase {

	public static void main(String[] args)throws Exception {
		Scanner scan = new Scanner(System.in);
          String url ="jdbc:mysql://localhost:3306/Employee";
          String username="root";
          String password="root";
          
          Connection con = null;
          PreparedStatement pstmt = null;
          ResultSet res =null;
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          System.out.println("Driver loaded successfully");
          
          con = DriverManager.getConnection(url, username, password);
          System.out.println("Database connection established");
          
          while(true) {
        	  System.out.println("----Employee database menu----");
        	  System.out.println("1.Add employee");
        	  System.out.println("2.view employee");
        	  System.out.println("3.update employee");
        	  System.out.println("4.Delete employee");
        	  System.out.println("5.Exit");
              
        	  int choice =  scan.nextInt();
        	  
        	  switch(choice) {
        	  
        	  case 1:
        	  addEmployee(con,scan);
        	  break;
        	  
        	  case 2:
        	  viewEmployee(con);
        	  break;
        	  
        	  case 3:
        	  updateEmployee(con,scan);
        	  break;
        	  
        	  case 4:
              deleteEmployee(con,scan);
              break;
              
        	  case 5:
                  con.close();
                  System.out.println("Exiting...");
                  return;
              default:
                  System.out.println("Invalid choice!");
          
        	  }



          }
          
          }
	
	static void addEmployee( Connection con,Scanner scan)throws Exception {
		System.out.println("Enter name:");
		String ename = scan.nextLine();
		System.out.println("Enter department:");
		String edept = scan.nextLine();
		System.out.println("Enter salary:");
		double salary = scan.nextDouble();
		
		PreparedStatement stmt = con.prepareStatement(
	            "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)"
	        );
	        stmt.setString(1, ename);
	        stmt.setString(2, edept);
	        stmt.setDouble(3, salary);
	        stmt.executeUpdate();
	        stmt.close();
	        System.out.println("Employee added successfully!");
	    }

	    static void viewEmployee(Connection conn) throws Exception {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

	        System.out.println("\nID\tName\tDepartment\tSalary");
	        while (rs.next()) {
	            System.out.printf("%d\t%s\t%s\t%.2f\n",
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("department"),
	                rs.getDouble("salary"));
	        }
	        rs.close();
	        stmt.close();
	    }

	    static void updateEmployee(Connection conn, Scanner sc) throws Exception {
	        System.out.print("Enter Employee ID to update: ");
	        int id = sc.nextInt();
	        sc.nextLine();
	        System.out.print("Enter new department: ");
	        String dept = sc.nextLine();
	        System.out.print("Enter new salary: ");
	        double salary = sc.nextDouble();

	        PreparedStatement stmt = conn.prepareStatement(
	            "UPDATE employees SET department = ?, salary = ? WHERE id = ?"
	        );
	        stmt.setString(1, dept);
	        stmt.setDouble(2, salary);
	        stmt.setInt(3, id);
	        int rows = stmt.executeUpdate();
	        stmt.close();

	        if (rows > 0) {
	            System.out.println("Employee updated successfully!");
	        } else {
	            System.out.println("Employee not found.");
	        }
	    }

	    static void deleteEmployee(Connection conn, Scanner sc) throws Exception {
	        System.out.print("Enter Employee ID to delete: ");
	        int id = sc.nextInt();

	        PreparedStatement stmt = conn.prepareStatement(
	            "DELETE FROM employees WHERE id = ?"
	        );
	        stmt.setInt(1, id);
	        int rows = stmt.executeUpdate();
	        stmt.close();

	        if (rows > 0) {
	            System.out.println("Employee deleted successfully!");
	        } else {
	            System.out.println("Employee not found.");
	        }
	    }
	
	
		
	}


