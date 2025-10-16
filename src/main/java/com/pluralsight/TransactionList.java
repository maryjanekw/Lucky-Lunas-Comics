package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionList {

    private List<Transaction> transaction = new ArrayList<>();

    public void loadTransaction(String fileName){
        transaction.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split("\\|");
                if (part.length == 5){
                    LocalDate date = LocalDate.parse(part[0]);
                    LocalTime time = LocalTime.parse(part[1]);
                    String description = part[2];
                    String vendor = part[3];
                    double total = Double.parseDouble(part[4]);
                    transaction.add(new Transaction(date, time, description, vendor,total));
                }
            }
            System.out.println("Transaction loading from " + fileName);
        }catch (FileNotFoundException e){
            System.out.println("Transaction not found.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveTransaction(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Transaction t : transaction) {
                writer.write(transaction.toString());
                writer.newLine();
            }
            System.out.println("Transaction saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAll() {
        if (transaction.isEmpty()) {
            System.out.println("Transaction list is empty.");
            return;
        }
        for(Transaction t : transaction){
            System.out.println();
        }
    }

    public Transaction findTransaction(String description, LocalDate date){
        return transaction.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst()
                .orElse(null);
    }

    public void addDeposit(LocalDate date, LocalTime time, String description, String vendor, double total){
//        if (findTransaction(description, date) != null) {
//            System.out.println("Transaction already exist: " + transaction + "\n");
//            return;
//        }
        transaction.add(new Transaction(date, time, description,vendor, total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|$" + total);
    }

    public void addDebitedTransaction(LocalDate date, LocalTime time, String description, String vendor, double total) {
//        if (findTransaction(description, date) != null) {
//            System.out.println("Transaction already exist: " + transaction + "\n");
//            return;
//        }
        transaction.add(new Transaction(date, time, description,vendor, total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|-$" + total);
    }
}

