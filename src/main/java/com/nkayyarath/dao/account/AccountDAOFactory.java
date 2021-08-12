package com.nkayyarath.dao.account;

public class AccountDAOFactory {
    private static AccountDAO dao;

    private AccountDAOFactory() {

    }

    public static AccountDAO getAccountDAO() {
        if (dao == null) {
            dao = new AccountDAOImpl();
        }
        return dao;
    }
}
