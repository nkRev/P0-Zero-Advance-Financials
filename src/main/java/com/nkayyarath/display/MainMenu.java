package com.nkayyarath.display;

public class MainMenu implements Display {
    @Override
    public void display() {
        System.out.println("***********************");
        System.out.println("1. Register");
        System.out.println("2. Customer Login");
        System.out.println("3. Employee Login");
        System.out.println("0. Quit");
        System.out.println("***********************");
    }
}

