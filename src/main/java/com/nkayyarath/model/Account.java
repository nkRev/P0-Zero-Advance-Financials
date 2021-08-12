package com.nkayyarath.model;

import java.sql.Date;

public class Account {
    private int accountNumber, customerID;
    private double balance, openingBalance, interest, depositAmt, withdrawAmt;
    private String accountName, accountStatus, transferStatus;
    private Date dateOpened;


    public Account() {
    }

    public Account(int accountNumber, int customerID, double balance, double openingBalance, double interest, double depositAmt, double withdrawAmt, String accountName, String accountStatus, String transferStatus, Date dateOpened) {
        this.accountNumber = accountNumber;
        this.customerID = customerID;
        this.balance = balance;
        this.openingBalance = openingBalance;
        this.interest = interest;
        this.depositAmt = depositAmt;
        this.withdrawAmt = withdrawAmt;
        this.accountName = accountName;
        this.accountStatus = accountStatus;
        this.transferStatus = transferStatus;
        this.dateOpened = dateOpened;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public double getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(double depositAmt) {
        this.depositAmt = depositAmt;
    }

    public double getWithdrawAmt() {
        return withdrawAmt;
    }

    public void setWithdrawAmt(double withdrawAmt) {
        this.withdrawAmt = withdrawAmt;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerID=" + customerID +
                ", balance=" + balance +
                ", interest=" + interest +
                ", accountName='" + accountName + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", transferStatus='" + transferStatus + '\'' +
                ", dateOpened=" + dateOpened +
                '}';
    }
}
