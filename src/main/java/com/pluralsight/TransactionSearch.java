package com.pluralsight;

import java.math.BigDecimal;
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

    // Shortcut Method for Date Range search
    public static void showTransactionDateRange(TransactionList list, LocalDate start, LocalDate end,
                                                String title){
        List<Transaction> transactions = list.getTransactions();
        boolean found = false;

        System.out.println(TransactionFormatter.ROSE_PINK + "\n====" + title + "====" + TransactionFormatter.RESET);
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


    // Search by Specific Date
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

    // Search by Vendor
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

    // Search by Exact Total
    public static void findExactTotal(TransactionList list) {
        Scanner read = new Scanner(System.in);

        System.out.println("Enter the total you would like to find: ");
        String input = read.nextLine().trim();
        System.out.println("Is this a search for Deposit, Debit, or All?: ");
        String type = read.nextLine().trim();

        try {
            BigDecimal target = parseAmountInput(input);
            boolean found = false;

            // Customized filter
            if (type.equalsIgnoreCase("deposit")) {
                list.displayDeposits();
            } else if (type.equalsIgnoreCase("debit")) {
                list.displayDebits();
            }else{
                list.displayAll();
            }

            TransactionFormatter.printHeader();
            for (Transaction t : list.getTransactions()) {
                BigDecimal amt = bd(t);
                if(amt.compareTo(target) == 0){
                    TransactionFormatter.printRow(t);
                    found = true;
                }
            }
        if (!found) {
            System.out.println("No transaction found for: " + target);
        }
    }catch(NumberFormatException a){
            System.out.println("Invalid amount. Try again.");
        }
    }

    // Search Total by Range
    public static void findByRange(TransactionList list) {
        Scanner read = new Scanner(System.in);

        System.out.print("Enter first amount: ");
        String firstRaw = read.nextLine().trim();
        System.out.print("Enter second amount: ");
        String secondRaw = read.nextLine().trim();
        System.out.println("Is this a search for Deposit, Debit, or All?: ");
        String type = read.nextLine().trim();

        try {
            BigDecimal first = parseAmountInput(firstRaw);
            BigDecimal second = parseAmountInput(secondRaw);

            // Guarantee min is the smallest total
            BigDecimal min = first.min(second);
            BigDecimal max = first.max(second);

            boolean found = false;

            // Customized filter
            if (type.equalsIgnoreCase("deposit")) {
                list.displayDeposits();
            } else if (type.equalsIgnoreCase("debit")) {
                list.displayDebits();
            }else{
                list.displayAll();
            }

            TransactionFormatter.printHeader();
            for (Transaction t : list.getTransactions()) {
                BigDecimal amt = bd(t);
                if (amt.compareTo(min) >= 0 && amt.compareTo(max) <= 0) {
                    TransactionFormatter.printRow(t);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No transactions found between " + min + " and " + max);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    // Helper Method for search Total
    private static BigDecimal parseAmountInput(String raw) throws NumberFormatException{
        if(raw == null) throw new NumberFormatException("null input");
        String s = raw.trim();
        if(s.startsWith("-$")){
            s = "-" + s.substring(2);
        } else if (s.startsWith("$")) {
            s = s.substring(1);
        }
        s = s.replace(",", ""); // for total > 100 (ex. 1,000)
        return  new BigDecimal(s);
    }
    private static BigDecimal bd(Transaction t){
        return BigDecimal.valueOf(t.getTotal());
    }

    // Search by Keywords
    public static void findKeyword(TransactionList list){
        Scanner read = new Scanner(System.in);

        System.out.println("Enter a Keyword to search: ");
        String keyword = read.nextLine().trim().toLowerCase();
        System.out.println("Is this a search for Deposit, Debit, or All?: ");
        String type = read.nextLine().trim();

        boolean found = false;

        TransactionFormatter.printHeader();
            for (Transaction t : list.getTransactions()){
                // Customized filter
                if (type.equalsIgnoreCase("deposit")) {
                    list.displayDeposits();
                } else if (type.equalsIgnoreCase("debit")) {
                    list.displayDebits();
                } else {
                    list.displayAll();
                }

                // Matching keyword in description or vendor
                String description = t.getDescription().toLowerCase();
                String vendor = t.getVendor().toLowerCase();

                if (description.contains(keyword) || vendor.contains(keyword)) {
                    TransactionFormatter.printRow(t);
                    found = true;
                }
            }
            if (!found){
                System.out.println("No transaction found containing keyword: " + keyword);
            }
        }
}