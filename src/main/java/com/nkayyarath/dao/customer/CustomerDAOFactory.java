package com.nkayyarath.dao.customer;

public class CustomerDAOFactory {
    private static CustomerDAO dao;

    private CustomerDAOFactory() {

    }

    public CustomerDAO getCustomerDAO() {
        if (dao == null) {
            dao = new CustomerDAOimpl();
        }
        return dao;
    }
}
