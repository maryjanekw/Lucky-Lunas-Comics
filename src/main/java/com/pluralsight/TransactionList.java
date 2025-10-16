package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionList {

    private List<Transaction> transaction = new ArrayList<>();
    private List<Transaction> newTransaction = new ArrayList<>();

    public void loadTransaction(String fileName){
//        transaction.clear();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] part = line.split("\\|");
                if (part.length >= 5){
                    transaction.add(new Transaction(part[0], part[1], part[2], part[3], Double.parseDouble(part[4])));
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
        loadTransaction("transactions.csv");
        for(Transaction t : transaction){
            System.out.println(t);
        }
    }

    public Transaction findTransaction(String description){
        return transaction.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst()
                .orElse(null);
    }
}

