package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String description;
    private String vendor;
    private String type;
    private double total;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, String type,double total) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.type = type;
        this.total = total;
    }

//    public Transaction(String s, String s1, String description, String vendor, String type,double total) {
//        }

    public Transaction(String s, String s1, String s2, String s3, double v) {
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public String getType(){
        return type;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" + type + total;
    }

}




