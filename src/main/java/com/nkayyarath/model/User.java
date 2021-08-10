package com.nkayyarath.model;

public class User {
    private String username;
    private String password;
    private int userID;
    private boolean isLoggedIn;

    public User() {
    }

    public User(String username, String password, int userID, boolean isLoggedIn) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.isLoggedIn = isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userID=" + userID +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
