package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    //variables
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String description;
    private String vendor;
    private double total;
    private String typeAndTotal; // stores the "-$" and "$" in the total


    //constructors
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, String typeAndTotal,double total) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.typeAndTotal = typeAndTotal;
        this.total = total;
    }

    public Transaction(String s, String s1, String s2, String s3, double v) {
    }

    public Transaction(String s, String s1, String s2, String s3, String s4) {
    }

    //getters
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
    public double getTotal() {
        return total;
    }
    public String getTypeAndTotal() {return typeAndTotal;}

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" + typeAndTotal + total;
    }

}




