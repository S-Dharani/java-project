package com.project;

import java.util.Scanner;

public class Calculator {
    public static void main(String[]args) {
    	Scanner scan = new Scanner(System.in);
    	boolean keeprun=true;
    	System.out.println("------Calculator-------");
    	
       while(keeprun) {
    	System.out.print("Enter the first number:" );
    	double num1=scan.nextDouble();
    	System.out.print("Enter the second number:");
    	double num2=scan.nextDouble();
    	System.out.println("------Enter your choice------");
        System.out.println("1.addition");
        System.out.println("2.Subtraction");
        System.out.println("3.Multiplication");
        System.out.println("4.Division");
        
        int choice=scan.nextInt();
        double ans=0;
        switch(choice){
        case 1:
        	ans=num1+num2;
            System.out.println(" sum of two number is:"+ans);
        break;
        case 2:
        	ans=num1-num2;
        	System.out.println(" subtraction of two number is:"+ ans);
        	break;
        case 3:
        	ans=num1*num2;
        	System.out.println(" multiplication of two number is:"+ans);
        	break;
        case 4:
        	ans=num1/num2;
        	System.out.println(" Division of two number is:"+ans);
        	break;
        case 5:
        	ans=num1+num2;
            System.out.println(" sum of two number is:"+ans);
            ans=num1-num2;
        	System.out.println(" subtraction of two number is:"+ ans);
        	ans=num1*num2;
        	System.out.println(" multiplication of two number is:"+ans);
        	ans=num1/num2;
        	System.out.println(" Division of two number is:"+ans);
        	default:
        		System.out.println("invalid number..........");
        }
        }
    }
}
