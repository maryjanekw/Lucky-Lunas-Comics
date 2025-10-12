package com.pluralsight;

import java.util.Scanner;

public class Home {

    public static void showHome(){
        Scanner read = new Scanner(System.in);
        Ledger list = new Ledger();
     //   list.loadFromCvs("transactions.csv");
        boolean running = true;

        while(running){
            System.out.println("\n Welcome to your Finance Tracker!");
            System.out.println("1. Add Deposit");
            System.out.println("2. Debited Transactions");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter your transaction.");
                    System.out.println("Date: ");
                    String date = read.nextLine();
                    System.out.println("Time: ");
                    String time = read.nextLine();
                    System.out.println("Item description: ");
                    String description = read.nextLine();
                    System.out.println("Vendor: ");
                    String vendor = read.nextLine();
                    System.out.println("Total: ");
                    String total = read.nextLine();
                    System.out.println("Added Transaction: " + date + "|" + time + "|" + description + "|" +vendor + "|$" + total);
            }
        }
    }
}
