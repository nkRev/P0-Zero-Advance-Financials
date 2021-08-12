package com.nkayyarath.driver;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
	    // write your code here
        LandingMenu landingMenu = new LandingMenu();
        landingMenu.mainMenu();

    }
}
