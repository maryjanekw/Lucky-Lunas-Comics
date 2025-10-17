package com.pluralsight;


import java.util.*;
import java.io.*;

public class Ledger {


    public static void showLedger() {
        Scanner read = new Scanner(System.in);
        TransactionList list = new TransactionList();

        list.loadTransaction("transactions.csv");

        boolean inLedger = true;

        while (inLedger) {
            System.out.println(TransactionFormatter.ORCHID + "\n Welcome to your Ledger!"
                    + TransactionFormatter.RESET);
            System.out.println(TransactionFormatter.SUNFLOWER_YELLOW + "----Ledger----" + TransactionFormatter.RESET);
            System.out.println("1. View all Transactions");
            System.out.println("2. View all Deposited Transactions");
            System.out.println("3. View all Debited Transactions");
            System.out.println("4. View Reports");
            System.out.println("5. Back to Home");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: // Display all Transactions
                    list.displayAll();
                    break;
                case 2: // Display Deposited Transactions only
                    list.displayDeposits();
                    break;
                case 3: // Display all Debited Transactions only
                    list.displayDebits();
                    break;
                case 4: // New Menu for Customized Search
                    Report.showReport(list);
                    break;
                case 5: // Back to Home
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
