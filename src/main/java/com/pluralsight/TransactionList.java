package com.pluralsight;

import java.io.*;
import java.time.*;
import java.util.*;

public class TransactionList {

    private List<Transaction> transaction = new ArrayList<>();
    public List<Transaction> newTransaction = new ArrayList<>();


    //lets you display .csv file
    public void loadTransaction(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split("\\|");
                if (part.length >= 5){
                    LocalDate date = LocalDate.parse(part[0].trim());
                    LocalTime time = LocalTime.parse(part[1].trim());
                    String description = part[2].trim();
                    String vendor = part[3].trim();
                    String typeAndTotal = part[4].trim(); //combine "-$" & "$" with total
                    double total;
                    if(typeAndTotal.startsWith("-$")){
                        total = Double.parseDouble(typeAndTotal.substring(2));
                    } else if (typeAndTotal.startsWith("$")) {
                        total = Double.parseDouble(typeAndTotal.substring(1));
                    }else{
                        total = Double.parseDouble(typeAndTotal);
                    }
                    transaction.add(new Transaction(date, time, description, vendor, typeAndTotal, total));
                }
            }
            System.out.println("Transaction loading from " + fileName);
        }catch (FileNotFoundException e){
            System.out.println("Transaction not found.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addDeposit(LocalDate date, LocalTime time, String description, String vendor,
                           String typeAndTotal, double total){
        Transaction t = new Transaction(date, time, description, vendor, typeAndTotal, total);
        transaction.add(t);
        newTransaction.add(t);
        System.out.println("Transaction added: " + t);
    }

    public void addDebitedTransaction(LocalDate date, LocalTime time, String description, String vendor,
                                      String typeAndTotal, double total) {
        Transaction t = new Transaction(date, time, description, vendor, typeAndTotal, total);
        transaction.add(t);
        newTransaction.add(t);
        System.out.println("Transaction added: " + t);
    }

    // save to .csv file
    public void saveTransaction(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Transaction t : newTransaction) {
                writer.write(t.toString());
                writer.newLine();
            }
            newTransaction.clear();
            System.out.println("Transaction saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //display functions
    public void displayAll() {
        if (transaction.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        TransactionFormatter.printHeader();
        for (Transaction t : transaction) {
            TransactionFormatter.printRow(t);
        }
        TransactionFormatter.printFooter();
    }

    public void displayDeposits() {
        List<Transaction> deposits = transaction.stream()
                .filter(t -> t.getTypeAndTotal().startsWith("$")) // deposit String type = "$"
                .toList();
        if (deposits.isEmpty()) {
            System.out.println("No deposits found.");
            return;
        }
        TransactionFormatter.printHeader();
        for (Transaction t : deposits) {
            TransactionFormatter.printRow(t);
        }
        TransactionFormatter.printFooter();
    }

    public void displayDebits(){
        List<Transaction> debits = transaction.stream()
                .filter(t -> t.getTypeAndTotal().startsWith("-$")) // debit String type = "-$"
                .toList();
        if (debits.isEmpty()) {
            System.out.println("No deposits found.");
            return;
        }
        TransactionFormatter.printHeader();
        for (Transaction t : debits) {
            TransactionFormatter.printRow(t);
        }
        TransactionFormatter.printFooter();
    }

    public List<Transaction> getTransactions() {
        return transaction;
    }
}

