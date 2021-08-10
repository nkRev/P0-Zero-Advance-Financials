package com.nkayyarath.dao.transactions;

public class TransactionDAOFactory {
    private static TransactionDAO dao;

    private TransactionDAOFactory() {

    }

    public TransactionDAO getTransactionDAO() {
        if (dao == null) {
            dao = new TransactionDAOImpl();
        }
        return dao;
    }
}
