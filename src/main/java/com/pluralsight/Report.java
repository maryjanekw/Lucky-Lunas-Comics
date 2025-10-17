package com.pluralsight;

import java.util.*;

public class Report {

    public static void showReport(TransactionList list){
        Scanner read = new Scanner(System.in);

        boolean inReport = true;

        while (inReport){
            System.out.println("\n Welcome to you Report!");
            System.out.println("Choose your custom search.");
            System.out.println("1. Month to Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year to Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Date");
            System.out.println("6. Search by Vendor");
            System.out.println("7. Search by Total");
            System.out.println("8. Search by Keywords");
            System.out.println("9. Back to Ledger");
            System.out.println("What would you like to do: ");

            int choice = read.nextInt();
            read.nextLine();

            switch (choice) {
                case 1: // search month to date
                    TransactionSearch.findMonthToDate(list);
                    break;
                case 2: // search previous month
                    TransactionSearch.findPreviousMonth(list);
                    break;
                case 3: //search year to date
                    TransactionSearch.findYearToDate(list);
                    break;
                case 4: //search previous year
                    TransactionSearch.findYearToDate(list);
                    break;
                case 5: // search by date
                    TransactionSearch.findDate(list);
                    break;
                case 6: // search by vendor
                    TransactionSearch.findVendor(list);
                    break;
                case 7: // search by total
                    break;
                case 8: // search by keyword
                    break;
                case 9: //back to ledger
                    inReport = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
