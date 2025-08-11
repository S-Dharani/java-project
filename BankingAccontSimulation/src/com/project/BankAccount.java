package com.project;

import java.util.ArrayList;
import java.util.Scanner;

class Account{
	private String accountHolder;
	private double balance;
	private ArrayList<String> transactionHistory;
	public Account(String accountHolder, double initialBalance) {
		super();
		this.accountHolder = accountHolder;
		this.balance = initialBalance;
		this.transactionHistory = new ArrayList<>();
		transactionHistory.add("Account opened with the balance : Rs."+initialBalance);
	}
	public void deposite(double amount) {
		if(amount>0) {
			balance = balance + amount;
			transactionHistory.add("Deposited: Rs."+ amount);
			System.out.println("Deposit successful... New balance : Rs."+ balance);
		}
		else {
			System.out.println("Invalid deposite amount..");
		}
	}
	public void withdraw(double amount) {
		if(amount >0 && amount<= balance) {
			balance = balance-amount;
			transactionHistory.add("Withdrew : Rs."+amount);
			System.out.println("Amount withdrawal successfully .New Balance: Rs."+ balance);
		}
		else if(amount>balance){
			System.out.println("Insufficient withdrawal balance..");
		}
		else {
			System.out.println("Invalid deposite amount ....");
		}
	}
	public void checkBalance() {
		System.out.println("Current Balance"+ balance);
	}
	public void transactionHistory() {
		System.out.println("---Transaction History---");
		for(String transaction: transactionHistory) {
			System.out.println(transaction);
		}
	}

}

public class BankAccount {

	public static void main(String[] args) {
     Scanner scan = new Scanner(System.in);
     System.out.println("Enter Account holder name:");
     String name = scan.nextLine();
     System.out.println("Enter initial deposite: Rs.");
     double initialBalance= scan.nextDouble();
     Account acc = new Account(name,initialBalance);
     
     int choice;
     do {
         System.out.println("------Bank menu--------");
         System.out.println("1.Deposite");
         System.out.println("2.withdraw");
         System.out.println("3.Checkbalance");
         System.out.println("4.Transaction History");
         System.out.println("5.Exit");
         System.out.print("Choose an option:");
         choice = scan.nextInt();
         switch(choice) {
         case 1:
         System.out.println("Enter deposite amount: Rs." );
         double depositeAmount = scan.nextDouble();
         acc.deposite(depositeAmount);
         break;
         case 2:
             System.out.println("Enter withdraw amount:  Rs.");
             break;
         case 3:
        	 acc.checkBalance();
        	 break;
         case 4:
        	 acc.transactionHistory();
        	 break;
         case 5:
        	 System.out.println("Thank you for banking with us!");
            break;
            default:
                System.out.println("Invalid option try again.");

         }
     }
      while(choice !=5);
     scan.close();
}

}
