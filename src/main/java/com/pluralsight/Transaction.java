package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String description;
    private String vendor;
    private double total;

    private String Transaction;

    public Transaction(String s, String s1, String s2, String s3, String s4, double v) {
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
}




