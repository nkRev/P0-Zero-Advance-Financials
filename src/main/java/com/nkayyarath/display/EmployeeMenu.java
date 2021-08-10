package com.nkayyarath.display;

public class EmployeeMenu implements Display{

    @Override
    public void display() {
        System.out.println("***********************");
        System.out.println("1. Approve or Reject an Account");
        System.out.println("2. View Customer's Accounts");
        System.out.println("3. View all transactions");
        System.out.println("0. To return");
        System.out.println("***********************");
    }
}
