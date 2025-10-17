package com.pluralsight;

import java.util.*;

public class Report {

    public static void showReport(TransactionList list){
        Scanner read = new Scanner(System.in);

        boolean inReport = true;

        while (inReport){
            System.out.println(TransactionFormatter.ORCHID + "Welcome to you Report!"
                    + TransactionFormatter.RESET);
            System.out.println(TransactionFormatter.SUNFLOWER_YELLOW + "----Report----" + TransactionFormatter.RESET);
            System.out.println("Choose your custom search.");
            System.out.println("1. Month-to-Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year-to-Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Date");
            System.out.println("6. Search by Vendor");
            System.out.println("7. Search by Exact Total");
            System.out.println("8. Search by Ranged Total");
            System.out.println("9. Search by Keywords");
            System.out.println("10. Back to Ledger");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: // Search Month-to-Date
                    TransactionSearch.findMonthToDate(list);
                    break;
                case 2: // Search Previous Month
                    TransactionSearch.findPreviousMonth(list);
                    break;
                case 3: // Search Year-to-Date
                    TransactionSearch.findYearToDate(list);
                    break;
                case 4: // Search Previous Year
                    TransactionSearch.findPreviousYear(list);
                    break;
                case 5: // Search by Date
                    TransactionSearch.findDate(list);
                    break;
                case 6: // Search by Vendor
                    TransactionSearch.findVendor(list);
                    break;
                case 7: // Search by Exact Total
                    TransactionSearch.findExactTotal(list);
                    break;
                case 8: // Search by Total Range
                    TransactionSearch.findByRange(list);
                    break;
                case 9: // Search by Keyword
                    TransactionSearch.findKeyword(list);
                    break;
                case 10: // Back to Ledger
                    inReport = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
