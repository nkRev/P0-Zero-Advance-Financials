package com.nkayyarath.display;

public class LandingDisplay implements Display {
    @Override
    public void display() {
        System.out.println("***********************");
        System.out.println("1. Become A Customer Today!");
        System.out.println("2. Register for an account.");
        System.out.println("3. Customer Login");
        System.out.println("4. Employee Login");
        System.out.println("0. Quit");
        System.out.println("***********************");
    }
}

