package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Home {

    public static void showHome() {
        Scanner read = new Scanner(System.in);
        TransactionList list = new TransactionList();
        list.loadTransaction("transactions.csv");
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
                case 1:
                    addDeposit(list, read);
                    break;
                case 2:
                    addDebitedTransactions(list, read);
                    break;
                case 3:
                    Ledger.showLedger();
                    break;
                case 4:
                    running = false;
                    System.out.println(" Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addDeposit(TransactionList list, Scanner read) {
        System.out.println("Enter your transaction.");
//        System.out.println("Date: ");
        LocalDate date = LocalDate.now();
//        System.out.println("Time: ");
        LocalTime time = LocalTime.now();
        System.out.println("Item description: ");
        String description = read.nextLine();
        System.out.println("Vendor: ");
        String vendor = read.nextLine();
        System.out.println("Total: ");
        double total = read.nextDouble();
//        System.out.println("Added Transaction: " + date + "|" + time + "|" + description + "|" +vendor
//                + "|$" + total);
        list.addDeposit(date,time, description, vendor,"$" ,total);
        list.saveTransaction("transactions.csv");
    }

    private static void addDebitedTransactions(TransactionList list, Scanner read){
        System.out.println("Enter your transaction.");
//        System.out.println("Date: ");
        LocalDate date = LocalDate.now();
//        System.out.println("Time: ");
        LocalTime time = LocalTime.now();
        System.out.println("Item description: ");
        String description = read.nextLine();
        System.out.println("Vendor: ");
        String vendor = read.nextLine();
        System.out.println("Total: ");
        double total = read.nextDouble();
//        System.out.println("Added Transaction: " + date + "|" + time + "|" + description + "|" + vendor
//                + "|-$" + total);
        list.addDebitedTransaction(date, time, description, vendor, "-$", total);
        list.saveTransaction("transactions.csv");
    }
}

