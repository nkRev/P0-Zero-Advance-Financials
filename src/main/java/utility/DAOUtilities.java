package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class DAOUtilities {
    private static final String CONNECTION_USERNAME = "root";
    private static final String CONNECTION_PASSWORD = "00112233445566";
    private static final String URL = "jbdc:MySQL://localhost:33066/project0";
    private static Connection connection;

    public static synchronized Connection getConnection() throws SQLException {

        //if the connection does not exist, then retrieve a new connection
        if (connection == null) {
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }

        //if connection is closed then retrieve a new connection
        if (connection.isClosed()) {
            System.out.println("Opening a new connection...");
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }
        return connection;
    }

    //DAO methods to get their implementations
}
