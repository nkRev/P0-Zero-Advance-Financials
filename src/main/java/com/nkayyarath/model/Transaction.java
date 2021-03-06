package com.nkayyarath.model;

import java.sql.Date;

public class Transaction {
   private double amount;
   private int accountNumber;
   private String transactionType;
   private Date date;

    public Transaction() {
    }

    public Transaction(double amount, int accountNumber, String transactionType, Date date) {
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", accountNumber=" + accountNumber +
                ", date=" + date +
                '}';
    }
}
