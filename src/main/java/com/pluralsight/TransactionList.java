package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class TransactionList {

    private List<Transaction> transaction = new ArrayList<>();
    private List<Transaction> newTransaction = new ArrayList<>();


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
                    String typeAndTotal = part[4].trim();

                    double total;
                    if(typeAndTotal.startsWith("-$")) {
                        total = Double.parseDouble(typeAndTotal.substring(2)) * -1;
                    } else {
                        total = Double.parseDouble(typeAndTotal.substring(1));
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

    public void addDeposit(LocalDate date, LocalTime time, String description, String vendor, String type,
                           double total){
        Transaction t = new Transaction(date, time, description, vendor, type,total);
        transaction.add(t);
        newTransaction.add(t);
        System.out.println("Transaction added: " + t);
    }

    public void addDebitedTransaction(LocalDate date, LocalTime time, String description, String vendor, String type,
                                      double total) {
        Transaction t = new Transaction(date, time, description, vendor, type, total);
        transaction.add(t);
        newTransaction.add(t);
        System.out.println("Transaction added: " + t);
    }

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

    // Deposit String type = "$"
    public void displayDeposits() {
        List<Transaction> deposits = transaction.stream()
                .filter(t -> "$".equals(t.getType()))
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

    // Debit String type = "-$"
    public void displayDebits(){
        List<Transaction> debits = transaction.stream()
                .filter(t -> "-$".equals(t.getType()))
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

    public Transaction findTransaction(String description){
        return transaction.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst()
                .orElse(null);
    }


}

