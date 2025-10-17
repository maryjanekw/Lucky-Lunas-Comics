package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Home {

    public static void showHome() {
        Scanner read = new Scanner(System.in);
        TransactionList list = new TransactionList();

        boolean running = true;

        while (running) {
            System.out.println(TransactionFormatter.ORCHID + "Welcome to your Finance Tracker!"
                    + TransactionFormatter.RESET);
            System.out.println(TransactionFormatter.SUNFLOWER_YELLOW + "----Home----" + TransactionFormatter.RESET);
            System.out.println("1. Add Deposit");
            System.out.println("2. Debited Transactions");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: // Add Deposited Transaction
                    addDeposit(list, read);
                    break;
                case 2: // Add  Debited Transactions
                    addDebitedTransactions(list, read);
                    break;
                case 3: // Go to Ledger
                    Ledger.showLedger();
                    break;
                case 4: // Exit Application
                    System.out.println(" Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


    // Customer input Add Deposit
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
        String typeAndTotal = "";
        list.addDeposit(date,time, description, vendor, typeAndTotal, total);
        read.nextLine();
        list.saveTransaction("transactions.csv");
    }

    // Customer input Add Debit
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
        String typeAndTotal = "";
        list.addDebitedTransaction(date, time, description, vendor, typeAndTotal, total);
        list.saveTransaction("transactions.csv");
    }
}

