package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionList {

    private String fileName;
    private List<Transaction> transaction = new ArrayList<>();

    public List<Transaction> newTransaction = new ArrayList<>();


    public void displayAll() {
        if (!transaction.isEmpty()) {
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

    public void addDeposit(LocalDate date, LocalTime time, String description, String vendor, String type, double total){
        if (findTransaction(description, date) != null) {
            System.out.println("Transaction already exist: " + transaction + "\n");
            return;
        }
        transaction.add(new Transaction(date, time, description,vendor, type, total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|$" + total);
    }

    public void addDebitedTransaction(LocalDate date, LocalTime time, String description, String vendor, String type,
                                      double total) {
        if (findTransaction(description, date) != null) {
            System.out.println("Transaction already exist: " + transaction + "\n");
            return;
        }
        transaction.add(new Transaction(date, time, description,vendor, type, total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|" +
                "-$" + total);
    }

    public void saveTransaction(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            for (Transaction t : transaction) {
                writer.write(transaction.toString());
                writer.newLine();
            }
            newTransaction.clear();
            System.out.println("Transaction saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace(); }
    }

    public void loadTransaction(String fileName){
        transaction.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split("\\|");
                if (part.length == 6) {
                    transaction.add(new Transaction(part[0], part[1], part[2], part[3], part[4], part[5],
                            Double.parseDouble(part[6])));
                }
            }
            System.out.println("Transaction loading from " + fileName);
        }catch (FileNotFoundException e){
            System.out.println("Transaction not found.");
        }catch (IOException e) {

        }
    }
}

