package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Ledger {
    private final List<Transaction> transaction = new ArrayList<>();

//    public void addTransaction(LocalDate date, LocalTime time, String description, String vendor, double total){
//        if(transaction != null){
//            System.out.println("Error!");
//            return;
//        }
//        transaction.add(new Transaction(date, time, description.trim(), vendor, total));
//        System.out.println("Added Transaction: " + date + "|" + time + "|" + description + "|" +vendor + "|" + total);
//    }
    public void displayAll(){
        if (transaction.isEmpty()){
            System.out.println("There are no listed Transactions.");
            return;
        }
        transaction.forEach(System.out::println);
    }

}
