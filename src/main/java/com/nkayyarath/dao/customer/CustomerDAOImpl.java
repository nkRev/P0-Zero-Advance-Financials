package com.nkayyarath.dao.customer;

import com.nkayyarath.model.Customer;
import com.nkayyarath.util.DAOUtilities;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

public class CustomerDAOImpl implements CustomerDAO {
    static PreparedStatement ps;
    Connection connection = null;

    public CustomerDAOImpl() {
        try {
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        final String sql ="INSERT INTO customers "
                + "(first_name, last_name, address, city, state, phone, ssn, join_date)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ps = connection.prepareStatement(sql);

        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getCity());
        ps.setString(5, customer.getState());
        ps.setString(6, customer.getSsn());
        ps.setString(7, customer.getPhoneNumber());
        ps.setDate(8, customer.getDate());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Customer account was successfully created!");
        } else {
            System.out.println("Have you tried turning it on and off again?");
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        final String sql = "UPDATE customers set first_name = ?, last_name = ?, address = ?, city = ?, state = ?, phone = ?, WHERE customer_id = ?";
        ps = connection.prepareStatement(sql);

        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getCity());
        ps.setString(5, customer.getState());
        ps.setString(6, customer.getPhoneNumber());
        ps.setInt(7, customer.getCustomerID());


        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("You have successfully updated your info!");
        } else {
            System.out.println("You may have missed something, please try again.");
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        final String sql = "DELETE FROM customers WHERE customer_id = ?";
        ps = connection.prepareStatement(sql);

        ps.setInt(1, id);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Customer deletion was successful!");
        } else {
            System.out.println("The Ninja's attacked our severs, please try again later...");
        }
    }

    @Override
    public List<Customer> getCustomer() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        final String sql = "SELECT * FROM customers";
        ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustomerID(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setState(rs.getString("state"));
            customer.setPhoneNumber(rs.getString("phone"));
            customer.setSsn(rs.getString("ssn"));
            customer.setDate(rs.getDate("join_date"));
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer customerByID(int id) throws SQLException {
        final String sql = "SELECT * FROM customers where customer_id = ?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Customer customer = new Customer();
        if (rs.next()) {
            customer.setCustomerID(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setState(rs.getString("state"));
            customer.setPhoneNumber(rs.getString("phone"));
            customer.setSsn(rs.getString("ssn"));
            customer.setDate(rs.getDate("join_date"));
        }
        return customer;
    }

    @Override
    public Customer getCustomerBySSN(String SSN) throws SQLException {
        final String sql = "SELECT * FROM customers where ssn = ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, SSN);
        ResultSet rs = ps.executeQuery();
        Customer customer = new Customer();
        if (rs.next()) {
            customer.setCustomerID(rs.getInt("customer_id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setState(rs.getString("state"));
            customer.setPhoneNumber(rs.getString("phone"));
            customer.setSsn(rs.getString("ssn"));
            customer.setDate(rs.getDate("join_date"));
        }

        return customer;
    }
}
