package com.nkayyarath.dao.customer;

public class CustomerDAOFactory {
    private static CustomerDAO dao;

    private CustomerDAOFactory() {

    }

    public static CustomerDAO getCustomerDAO() {
        if (dao == null) {
            dao = new CustomerDAOImpl();
        }
        return dao;
    }
}
