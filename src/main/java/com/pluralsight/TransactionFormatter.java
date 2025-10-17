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
        System.out.printf("%-12s %-10s %-35s %-20s %-10s%n",
                "Date","Time","Description","Vendor","Total");
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
    }

    //transaction outline
    public static void printRow(Transaction t){
        String color = "";
        if(t.getType().equalsIgnoreCase("$")){
            color = GREEN;
        }
        if(t.getType().equalsIgnoreCase("-$")){
            color = RED;
        }else{
        }
        System.out.printf(color + "%-12s %-10s %-35s %-20s %-10s" + RESET + "%n",
                t.getDate(), t.getTime(), t.getDescription(),
                t.getVendor(), t.getType(), t.getTotal());
    }

    public static void printFooter() {
        System.out.println(CYAN + "=================================================================================" +
                "========" + RESET);
    }
}
