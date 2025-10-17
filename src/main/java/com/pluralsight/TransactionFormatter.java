package com.pluralsight;

public class TransactionFormatter {

    // Colors
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String SUNFLOWER_YELLOW = "\u001B[38;2;255;218;3m";
    public static final String ORCHID = "\u001B[38;2;218;112;214m";
    public static final String ROSE_PINK = "\u001B[38;2;255;0;204m";



    // Transaction Header outline
    public static void printHeader(){
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
        System.out.printf("%-12s %-10s %-35s %-20s %-15s%n",
                "Date","Time","Description","Vendor","Total");
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
    }

    // Transaction outline
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

    // Transaction Footer outline
    public static void printFooter() {
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
    }
}
