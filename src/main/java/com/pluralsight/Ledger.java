package com.pluralsight;


import java.util.Scanner;

public class Ledger {

    public void loadFromCvs(String fileName) {
    }

    public static void showLedger() {
        Scanner read = new Scanner(System.in);
        Ledger list = new Ledger();
        list.loadFromCvs("transactions.csv");
        boolean running = true;

        while (running) {
            System.out.println("\n Welcome to your Ledger!");
            System.out.println("1. View all Transactions");
            System.out.println("2. View all Deposited Transactions");
            System.out.println("3. View al Debited Transactions");
            System.out.println("4. View Repots");
            System.out.println("5. Back to Home");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: //display all transactions
                    break;
                case 2: //display deposited transactions only
                    break;
                case 3: //display all debited transactions only
                    break;
                case 4: // customized search
                    break;
                case 5: //back to home
                    Home.showHome();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
