package com.nkayyarath.display;

public class CustomerMenu implements Display {

    @Override
    public void display() {
        System.out.println("***********************");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Money Transfer");
        System.out.println("4. Apply for a new account");
        System.out.println("5. View an account balance");
        System.out.println("0. Logout");
        System.out.println("***********************");
    }
}
