package com.nkayyarath.dao.user;

import com.nkayyarath.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void createUser(User user) throws SQLException;

    User retrieveUserID(int userID) throws SQLException;

    void updateUsers(User user) throws SQLException;

    void rmUser(int userID) throws SQLException;

    List<User> getUsers() throws SQLException;

    void authenticateCustomerLogin(String username, String password) throws SQLException;

    void authenticateEmployeeLogin(String username, String password) throws SQLException;


}
