package com.nkayyarath.dao.customer;

import com.nkayyarath.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int id) throws SQLException;

    List<Customer> getCustomer() throws SQLException;

    Customer customerByID(int id) throws SQLException;

    Customer getCustomerBySSN(String SSN) throws SQLException;
}
