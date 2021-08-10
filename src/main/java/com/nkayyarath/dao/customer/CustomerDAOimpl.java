package com.nkayyarath.dao.customer;

import com.nkayyarath.model.Customer;
import com.nkayyarath.util.DAOUtilities;
import org.ietf.jgss.GSSName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerDAOimpl implements CustomerDAO{
    static PreparedStatement ps;
    Connection connection;
    ResourceBundle rb = ResourceBundle.getBundle("com/nkayyarath/dao/customer/CustQuery");

    public CustomerDAOimpl() {
        try{
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        final String sql = rb.getString("addCustomer");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, customer.getCustomerID());
        ps.setString(2, customer.getFirstName());
        ps.setString(3, customer.getLastName());
        ps.setString(4, customer.getAddress());
        ps.setString(5, customer.getCity());
        ps.setString(5, customer.getState());
        ps.setString(6, customer.getSsn());
        ps.setDate(7, customer.getDate());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Customer account was successfully created!");
        } else {
            System.out.println("Have you tried turning it on and off again?");
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        final String sql = rb.getString("updateCustomer");
        ps = connection.prepareStatement(sql);

        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getCity());
        ps.setString(5, customer.getState());
        ps.setString(6, customer.getPhoneNumber());
        ps.setInt(7, customer.getCustomerID());

        int count = ps.executeUpdate();
        if (count>0){
            System.out.println("You have successfully updated your info!");
        }else{
            System.out.println("You may have missed something, please try again.");
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        final String sql = rb.getString("deleteCustomer");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, id);

        int count = ps.executeUpdate();
        if (count>0){
            System.out.println("Customer deletion was successful!");
        }else{
            System.out.println("The Ninja's attacked our severs, please try again later...");
        }
    }

    @Override
    public List<Customer> getCustomer() throws SQLException {
        List<Customer>  customerList = new ArrayList<>();
        final String sql = rb.getString("selectAll");
        ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
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
        final String sql = rb.getString("selectByCustomerID");
        ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Customer customer = new Customer();
        if(rs.next()){
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
