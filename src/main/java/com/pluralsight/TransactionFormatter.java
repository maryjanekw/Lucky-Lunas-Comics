package com.pluralsight;

public class TransactionFormatter {

    //colors
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";


    //transaction header
    public static void printHeader(){
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
        System.out.printf("%-12s %-10s %-35s %-20s %-15s%n",
                "Date","Time","Description","Vendor","Total");
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
    }

    //transaction outline
    public static void printRow(Transaction t) {
        String totalText = t.getTypeAndTotal();
        String color = RESET;
        if (totalText.startsWith("$")) {
            color = GREEN;
        }else if (totalText.startsWith("-$")){
            color = RED;
        }
        System.out.printf("%-12s %-10s %-35s %-20s" + color + " %-15s" + RESET + "%n",
                t.getDate(), t.getTime(), t.getDescription(),
                t.getVendor(),totalText);
    }

    public static void printFooter() {
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
    }
}
