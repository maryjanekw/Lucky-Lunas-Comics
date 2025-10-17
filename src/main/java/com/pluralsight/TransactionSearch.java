package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TransactionSearch {

    // Month-to-Date search
    public static void findMonthToDate(TransactionList list) {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        showTransactionDateRange(list, firstDay, today,
                "Month-to-Date(" + firstDay + " to " + today + ")");
    }

    // Previous Month search
    public static void findPreviousMonth(TransactionList list){
        LocalDate today = LocalDate.now();
        LocalDate firstDayPreviousMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayPreviousMonth  = today.withDayOfMonth(1).minusDays(1);
        showTransactionDateRange(list, firstDayPreviousMonth, lastDayPreviousMonth,
                "Previous Month(" + firstDayPreviousMonth + " to " + lastDayPreviousMonth + ")");


    }

    // Year-to-Date search
    public  static void  findYearToDate(TransactionList list){
        LocalDate today = LocalDate.now();
        LocalDate firstDayYear = today.withDayOfYear(1);
        showTransactionDateRange(list, today, firstDayYear,
                "Year-to-Date(" + firstDayYear + " to " + today + ")");
    }

    // Previous Year search
    public static void findPreviousYear(TransactionList list){
        LocalDate firstDayPreviousYear = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate lastDayPreviousYear = LocalDate.now().withDayOfYear(1).minusDays(1);
        showTransactionDateRange(list, firstDayPreviousYear,lastDayPreviousYear,
                "Year-to-date(" + firstDayPreviousYear + " to " + lastDayPreviousYear + ")");

    }

    // Shortcut method for date range search
    public static void showTransactionDateRange(TransactionList list, LocalDate start, LocalDate end,
                                                String title){
        List<Transaction> transactions = list.getTransactions();
        boolean found = false;

        System.out.println("\n====" + title + "====");
        TransactionFormatter.printHeader();

        for(Transaction t: transactions){
            LocalDate date = t.getDate();
            if ((date.isEqual(start) || date.isAfter(start) && (date.isBefore(end) || date.isEqual(end)))) {
                TransactionFormatter.printRow(t);
                found = true;
            }
        }
        if(!found){
            System.out.println("No transaction found with in this date range.");
        }
    }


    //search by specific date
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

    //search by vendor
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
