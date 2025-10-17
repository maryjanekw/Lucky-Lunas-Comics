package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TransactionSearch {

    public static void findDate(TransactionList list){
        Scanner read = new Scanner(System.in);

        System.out.println("Enter date to search (yyyy-MM-dd): ");
        String input = read.nextLine();
        try{
            LocalDate searchDate = LocalDate.parse(input);
            List<Transaction> transactions = list.getTransactions();
            boolean found = false;

            TransactionFormatter.printHeader();
            for (Transaction t : transactions) {
                if (t.getDate().equals(searchDate)) {
                    TransactionFormatter.printRow(t);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No transactions found for " + searchDate);
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public static void findVendor(TransactionList list) {
        Scanner read = new Scanner(System.in);

        System.out.println("Enter vendor name: ");
        String vendorInput = read.nextLine().trim().toLowerCase();

        List<Transaction> transactions = list.getTransactions();
        boolean found = false;

        TransactionFormatter.printHeader();
        for (Transaction t : transactions) {
            String vendor = t.getVendor().toLowerCase();
            if (vendor.contains(vendorInput)) {
                TransactionFormatter.printRow(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Vendor not found: " + vendorInput);
        }
    }
}
