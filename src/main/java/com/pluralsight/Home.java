package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Home {

    public static void showHome() {
        Scanner read = new Scanner(System.in);
        TransactionList list = new TransactionList();
//        list.loadTransaction("transactions.csv");

        boolean running = true;

        while (running) {
            System.out.println("\n Welcome to your Finance Tracker!");
            System.out.println("----Home----");
            System.out.println("1. Add Deposit");
            System.out.println("2. Debited Transactions");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: //add Customer Transaction
                    addDeposit(list, read);
                    break;
                case 2: //add transactions using store money
                    addDebitedTransactions(list, read);
                    break;
                case 3: //go to the ledger
                    Ledger.showLedger();
                    break;
                case 4: //exit
                    System.out.println(" Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addDeposit(TransactionList list, Scanner read) {
        System.out.println("Enter your transaction.");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println("Item description: ");
        String description = read.nextLine();
        System.out.println("Vendor: ");
        String vendor = read.nextLine();
        System.out.println("Total: ");
        double total = read.nextDouble();
        list.addDeposit(date,time, description, vendor,total);
        list.saveTransaction("transactions.csv");
    }

    private static void addDebitedTransactions(TransactionList list, Scanner read){
        System.out.println("Enter your transaction.");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println("Item description: ");
        String description = read.nextLine();
        System.out.println("Vendor: ");
        String vendor = read.nextLine();
        System.out.println("Total: ");
        double total = read.nextDouble();
        list.addDebitedTransaction(date, time, description, vendor, total);
        list.saveTransaction("transactions.csv");
    }
}

