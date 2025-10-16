package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String description;
    private String vendor;
    private double total;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double total) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.total = total;
    }

    public Transaction(String s, String s1, String description, String vendor, double total) {
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

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return date + "|" + time.format(timeFormatter) + "|" + description + "|" + vendor + "|" + total;
    }

}




