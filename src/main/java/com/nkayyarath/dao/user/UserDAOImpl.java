package com.nkayyarath.dao.user;

import com.nkayyarath.model.User;
import com.nkayyarath.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDAOImpl implements UserDAO{
    static PreparedStatement ps;
    Connection connection;
    ResourceBundle rb = ResourceBundle.getBundle("com/nkayyarath/dao/user/UserQuery.properties");

    public UserDAOImpl() {
        try{
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) throws SQLException {
        final String sql = rb.getString("addUser");
        ps = connection.prepareStatement(sql);

        ps.setString(1, "username");
        ps.setString(2, "password");

        int count = ps.executeUpdate();
        if (count>0){
            System.out.println("User account created.");
        }else{
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
        final String sql = rb.getString("selectUserByID");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        User user = new User();

        if(rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }

    /**
     * allows user to change password
     * maybe if I have enough time include a way to do it at login
     * or Employee must do it for user like at actual bank.
     * @param user
     * @throws SQLException
     */
    @Override
    public void updateUsers(User user) throws SQLException {
        final String sql = rb.getString("updateUser");
        ps = connection.prepareStatement(sql);

        ps.setString(1, user.getPassword());
        ps.setString(2, user.getUsername());

        int count = ps.executeUpdate();
        if (count>0){
            System.out.println("User password is successfully updated!");
        }else{
            System.out.println("Are you sure that's your username?");
        }
    }

    /**
     * ONLY EMPLOYEES ARE ALLOWED TO DELETE USER ACCOUNTS
     * SINCE USERS DO NOT KNOW THEY'RE ID NUMBER
     *
     * db may be setup to delete all accounts tied to the customer...
     * @param userID
     */
    @Override
    public void rmUser(int userID) throws SQLException {
        final String sql = rb.getString("deleteUser");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, userID);

        int count = ps.executeUpdate();
        if (count>0){
            System.out.println("User successfully deleted.");
        }else{
            System.out.println("Are you sure that's the correct User ID?\n"
                    +"Try viewing all users and confirm it is the correct User ID.");
        }
    }

    /**
     * ONLY EMPLOYEES ARE ALLOWED TO VIEW ALL USERS AND THEIR INFORMATION
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> getUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        final String sql = rb.getString("selectAllUsers");
        ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
           User user = new User();
           user.setUsername(rs.getString("username"));
           user.setPassword(rs.getString("password"));
           user.setUserID(rs.getInt("userID"));
           userList.add(user);
        }

        return userList;
    }

    @Override
    public String login(String username) {
        return null;
    }
}
