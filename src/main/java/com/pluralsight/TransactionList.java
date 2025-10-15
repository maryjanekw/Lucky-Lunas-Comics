package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionList {

    private String fileName;
    private List<Transaction> transactions;

    public void TransactionList(){
        transactions = new ArrayList<>();
    }
    private final List<Transaction> transaction = new ArrayList<>();

    public String Transaction(String s, String s1, String trim, String description, String vendor, double total) {
        return s;
    }

    public void  displayAll() {
        if (transaction.isEmpty()) {
            transaction.forEach(System.out::println);
        } else {
            System.out.println("Your transaction list is empty.");
            return;
        }
    }

    public Transaction findTransaction(String description, LocalDate date){
        return transaction.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst()
                .orElse(null);
    }

    public void addDeposit(LocalDate date, LocalTime time, String description, String vendor, double total) {
        if (findTransaction(description, date) != null) {
            System.out.println("Transaction already exist: " + transaction);
            return;
        }
        transaction.add(new Transaction(date, time, description,vendor,total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|$" + total);
    }

    public void addDebitedTransaction(LocalDate date, LocalTime time, String description, String vendor, double total) {
        if (findTransaction(description, date) != null) {
            System.out.println("Transaction already exist: " + transaction);
            return;
        }
        transaction.add(new Transaction(date, time, description,vendor,total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|-$" + total);
    }

    public void saveTransaction(String fileName, Transaction[] transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv"))) {
            for (Transaction transaction : transactions);
            Transaction transaction = null;
            writer.write(transaction.getDate() + "|" + transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +transaction.getVendor() + "|$" + transaction.getTotal());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void loadTransaction(String fileName){
        transactions.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] part = line.split("|", 5);
                if(part.length == 5) {
                    transactions.add(new Transaction(part[0], part[1], part[2], part[3], part[4],
                            Double.parseDouble(part[5])));
                } else {
                }
            }
        }catch (IOException e) {

        }
    }
}

