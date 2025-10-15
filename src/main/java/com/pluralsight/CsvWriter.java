package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class CsvWriter {

    private String fileName;
    private List<Transaction> transactions;

    public void Transaction(){
        transactions = new ArrayList<>();
    }

    public void saveToCsv(String fileName, Transaction[] transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Transaction transaction : transactions);
            Transaction transaction = null;
            writer.write(transaction.getDate() + "|" + transaction.getTime() + "|" +
                        transaction.getDescription() + "|" +transaction.getVendor() + "|$" + transaction.getTotal());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void loadFromCsv(String fileName){
        transactions.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                String[] part = line.split("|", 4);
                if(part.length == 4) {
                    transactions.add(new Transaction(part[0], part[1], part[2], part[3], part[4],
                            Double.parseDouble(part[5])));
                } else {
                }
            }
        }catch (IOException e) {

        }
    }
}

