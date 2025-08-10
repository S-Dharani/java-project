package com.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NoteApp {
    private static final String FILE_NAME = "notes.txt";
public static void main(String[] args) {

	          Scanner scanner = new Scanner(System.in);
	          int choice;

	          do {
	              System.out.println("\n--- Text-Based Notes Manager ---");
	              System.out.println("1. Add Note");
	              System.out.println("2. View Notes");
	              System.out.println("3. Delete All Notes");
	              System.out.println("4. Exit");
	              System.out.print("Enter your choice: ");
	              choice = scanner.nextInt();
	              scanner.nextLine(); // clear buffer

	              switch (choice) {
	                  case 1:
	                      addNote(scanner);
	                      break;
	                  case 2:
	                      viewNotes();
	                      break;
	                  case 3:
	                      deleteNotes();
	                      break;
	                  case 4:
	                      System.out.println("Exiting... Goodbye!");
	                      break;
	                  default:
	                      System.out.println("Invalid choice. Try again.");
	              }
	          } while (choice != 4);

	          scanner.close();
	      }

	      private static void addNote(Scanner scanner) {
	          try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
	              System.out.print("Enter your note: ");
	              String note = scanner.nextLine();
	              writer.write(note);
	              writer.newLine();
	              System.out.println("Note saved successfully!");
	          } catch (IOException e) {
	              System.out.println("Error writing note: " + e.getMessage());
	          }
	      }

	      private static void viewNotes() {
	          try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	              String line;
	              System.out.println("\n--- Your Notes ---");
	              boolean empty = true;
	              while ((line = reader.readLine()) != null) {
	                  System.out.println("- " + line);
	                  empty = false;
	              }
	              if (empty) {
	                  System.out.println("(No notes found)");
	              }
	          } catch (FileNotFoundException e) {
	              System.out.println("(No notes file found yet)");
	          } catch (IOException e) {
	              System.out.println("Error reading notes: " + e.getMessage());
	          }
	      }

	      private static void deleteNotes() {
	          try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
	              // Empty file
	              System.out.println("All notes deleted successfully!");
	          } catch (FileNotFoundException e) {
	              System.out.println("(No notes file found yet)");
	          }
	      }
	  

 
	  
	}


