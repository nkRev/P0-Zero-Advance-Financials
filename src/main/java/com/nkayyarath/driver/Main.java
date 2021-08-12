package com.nkayyarath.driver;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Logger menuLog = Logger.getRootLogger();
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
	    // write your code here
        menuLog.info("Program started");
        LandingMenu landingMenu = new LandingMenu();
        landingMenu.mainMenu();

    }
}
