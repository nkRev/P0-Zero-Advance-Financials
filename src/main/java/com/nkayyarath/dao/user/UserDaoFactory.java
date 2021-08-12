package com.nkayyarath.dao.user;

public class UserDaoFactory {
    private static UserDAO dao;

    private UserDaoFactory() {

    }

    public static UserDAO getUserDAO() {
        if (dao == null) {
            dao = new UserDAOImpl();
        }
        return dao;
    }
}
