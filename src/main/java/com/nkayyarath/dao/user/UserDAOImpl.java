package com.nkayyarath.dao.user;

import com.nkayyarath.driver.CMenu;
import com.nkayyarath.driver.EmpMenu;
import com.nkayyarath.model.User;
import com.nkayyarath.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    static PreparedStatement ps;
    Connection connection = null;

    public UserDAOImpl() {
        try {
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) throws SQLException {
        final String sql = "INSERT INTO users (username, password, user_type) VALUES (?, ?, ?)";
        ps = connection.prepareStatement(sql);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("User account created.");
        } else {
            System.out.println("This usually doesn't happen, I swear!");
        }
    }

    /**
     * ONLY EMPLOYEES ARE ALLOWED TO RETRIEVE A USER BY ID
     *
     * @param userID
     * @return user obj from database
     * @throws SQLException
     */
    @Override
    public User retrieveUserID(int userID) throws SQLException {
        final String sql = "SELECT * FROM users WHERE userID = ?";
        ps = connection.prepareStatement(sql);

        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        User user = new User();

        if (rs.next()) {
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }

    /**
     * allows user to change password
     * maybe if I have enough time include a way to do it at login
     * or Employee must do it for user like at actual bank.
     *
     * @param user
     * @throws SQLException
     */
    @Override
    public void updateUsers(User user) throws SQLException {
        final String sql = "UPDATE users SET password = ? WHERE username = ?";
        ps = connection.prepareStatement(sql);

        ps.setString(1, user.getPassword());
        ps.setString(2, user.getUsername());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("User password is successfully updated!");
        } else {
            System.out.println("Are you sure that's your username?");
        }
    }

    /**
     * ONLY EMPLOYEES ARE ALLOWED TO DELETE USER ACCOUNTS
     * SINCE USERS DO NOT KNOW THEY'RE ID NUMBER
     * <p>
     * db may be setup to delete all accounts tied to the customer...
     *
     * @param userID
     */
    @Override
    public void rmUser(int userID) throws SQLException {
        final String sql = "DELETE FROM users WHERE userID = ?";
        ps = connection.prepareStatement(sql);

        ps.setInt(1, userID);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("User successfully deleted.");
        } else {
            System.out.println("Are you sure that's the correct User ID?\n"
                    + "Try viewing all users and confirm it is the correct User ID.");
        }
    }

    /**
     * ONLY EMPLOYEES ARE ALLOWED TO VIEW ALL USERS AND THEIR INFORMATION
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> getUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        final String sql = "SELECT * FROM users";
        ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUserID(rs.getInt("userID"));
            userList.add(user);
        }

        return userList;
    }

    @Override
    public void authenticateCustomerLogin(String username, String password) throws SQLException {
        final String sql = "Select * FROM users where username = ? AND password = ?";
        ps = connection.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        String dbUsername, dbPw;

        while (rs.next()) {
            dbUsername = rs.getString("username");
            dbPw = rs.getString("password");
            if (username.equals(dbUsername) && password.equals(dbPw)) {
                System.out.println("Login successful");
                CMenu.customerMenu();
                break;
            } else {
                System.out.println("Invalid login information");
            }
        }
    }

    @Override
    public void authenticateEmployeeLogin(String username, String password) throws SQLException {
        final String sql = "Select * FROM users where username = ? AND password = ? AND user_type = ?";
        ps = connection.prepareStatement(sql);
        String dbUsername, dbPw, dbUserType, userType = "admin";

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, userType);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {
            dbUsername = rs.getString("username");
            dbPw = rs.getString("password");
            dbUserType = rs.getString("user_type");

            if (username.equals(dbUsername) && password.equals(dbPw) && userType.equals(dbUserType)) {
                System.out.println("Login successful");
                EmpMenu.employeeMenu();
                break;
            } else {
                System.out.println("Invalid login information");
            }
        }
    }


}
