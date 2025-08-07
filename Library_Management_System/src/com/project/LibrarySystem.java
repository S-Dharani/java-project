package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book{
	private int  bookid;
	private String bTitle;
	private String author;
	private boolean isAvailable;
	public Book(int bookid, String bTitle, String author, boolean isAvailable) {
		this.bookid = bookid;
		this.bTitle = bTitle;
		this.author = author;
		this.isAvailable = isAvailable;
	}
	public int getBookid() {
		return bookid;
	}
	
	public String getbTitle() {
		return bTitle;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	public void borrow() {
		isAvailable = false;
	}
	public void returnBook() {
		isAvailable= true;
	}
	public String toString() {
		return "Book id:" + bookid + ",BookTitle:"+bTitle+",Author:"+author+ ",IsAvailable:"+isAvailable;
	}
   }
class User{
	private int userId;
	private String userName;
	
	public User(int userId, String userName) {
        this.userId = userId;
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String toString() {
		return "UserID:"+userId+"UserName:"+userName;
	}
	}
class Library{
	 private List<Book> books= new ArrayList<>();
	    private List<User> users= new ArrayList<>();
	    
	    public void addBook(Book book) {
	    	books.add(book);
	    	System.out.println("Book added.");
	    }
	    public void registerUser(User user) {
	    	users.add(user);
	    	System.out.println("User registerd.");
	    }
	    public void borrowBook(int bookId) {
	    	for(Book book:books) {
	    		if(book.getBookid()== bookId &&!book.isAvailable()) {
	    			book. borrow();
	    			System.out.println("Book borrowed successfully");
	    			return;
	    		}
	    		}
	    	System.out.println("book not available");
	    }
	    public void returnBook(int bookId) {
	    	for(Book book: books) {
	    		if(book.getBookid()==bookId && !book.isAvailable()) {
	    			book.returnBook();
               System.out.println("Book returned");
                    return;
	    		}
	    	}
	    
	    	System.out.println("Invalid book Id or book was not borrow.");
	    		}
	    public void showAllBooks(){
    		for(Book book : books) {
    			System.out.println(book);
	    }
		}
        
	    public void showAllUsers(){
	    	for(User user: users) {
	    		System.out.println(user);
	    	}
	    }
	
}
public class LibrarySystem {
    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      Library lib = new Library();
      while(true) {
    	  System.out.println("------Library Management System-------");
    	  System.out.println("1.Add book");
    	  System.out.println("2.Registerd book");
    	  System.out.println("3.Borrow book");
    	  System.out.println("4.Return book");
    	  System.out.println("5.Show all books");
    	  System.out.println("6.Show all users");
    	  System.out.println("7.Exit");
    	  System.out.println("----Enter your choice----");
    	  
    	  int choice = scan.nextInt();
    	  switch(choice) {
    	  case 1 :
        	  System.out.println("Enter you book id:");
               int bid = scan.nextInt();
              System.out.println("Enter your book title:");
                String bname= scan.nextLine();
          	  System.out.println("Enter your book author");
          	     String bauthor = scan.nextLine();
          	  lib.addBook(new Book(bid,bname,bauthor, false));
               break;
    	  case 2 :
        	  System.out.println("Enter user Id:");
                int uid = scan.nextInt();
          	  System.out.println("Enter user name:");
                String uname= scan.nextLine();
                lib.registerUser( new User(uid ,uname));
                break;
    	  case 3 :
    	  System.out.println("Enter book id to return: ");
    	      int bBid = scan.nextInt();
    	      lib.borrowBook(bBid);
    	      break;
    	  case 4:
        	  System.out.println("Enter book id to return:");
        	  int rBid = scan.nextInt();
              lib.returnBook(rBid);
              break;
    	  case 5:
    		  lib.showAllBooks();
    		  break;
    	  case 6:
    		  lib.showAllUsers();
    		  break;
    	  case 7:
    		  System.out.println("Existing systen...");
    		  
           scan.close();
           System.exit(0);
           default:
       System.out.println("Invalid choice");
    	  }


    	  
      }
}
}
