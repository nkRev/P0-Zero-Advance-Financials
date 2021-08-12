package com.nkayyarath.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtilities {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/project0";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0011223344";

    public DAOUtilities() {
    }

    public static Connection getConnection() throws SQLException {

        //if the connection does not exist, then retrieve a new connection
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        //if connection is closed then retrieve a new connection
        if (connection.isClosed()) {
            System.out.println("Opening a new connection...");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
