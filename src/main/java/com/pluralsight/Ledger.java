package com.pluralsight;


import java.util.Scanner;
import java.io.*;

public class Ledger {


    public static void showLedger() {
        Scanner read = new Scanner(System.in);
        TransactionList list = new TransactionList();
        list.loadTransaction("transactions.csv");

        boolean inLedger = true;

        while (inLedger) {
            System.out.println("\n Welcome to your Ledger!");
            System.out.println("1. View all Transactions");
            System.out.println("2. View all Deposited Transactions");
            System.out.println("3. View all Debited Transactions");
            System.out.println("4. View Reports");
            System.out.println("5. Back to Home");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: //display all transactions
                    list.displayAll();
                    break;
                case 2: //display deposited transactions only
                    list.displayDeposits();
                    break;
                case 3: //display all debited transactions only
                    list.displayDebits();
                    break;
                case 4: // customized search
                    break;
                case 5: //back to home
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
