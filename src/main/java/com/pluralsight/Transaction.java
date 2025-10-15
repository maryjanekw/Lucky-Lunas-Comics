package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private  String description;
    private String vendor;
    private double total;

    private final List<Transaction> transaction = new ArrayList<>();
    public Transaction(LocalDate date, LocalTime time, String trim, String description, String vendor, double total) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.total = total;
    }

    public Transaction(LocalDate date, LocalTime time, String trim, String vendor, double total) {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addDeposit(LocalDate date, LocalTime time, String description, String vendor, double total){
        if(findTransaction(description, date) != null) {
            System.out.println("Transaction already exist: " + transaction);
            return;
        }
        transaction.add(new Transaction(date, time, description.trim(), vendor, total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|$" + total);
    }

    public void addDebitedTransaction(LocalDate date, LocalTime time, String description, String vendor, double total){
        if(findTransaction(description, date) != null) {
            System.out.println("Transaction already exist: " + transaction);
            return;
        }
        transaction.add(new Transaction(date, time, description.trim(), vendor, total));
        System.out.println("Transaction added: " + date + "|" + time + "|" + description + "|" + vendor + "|-$" + total);
    }
}
