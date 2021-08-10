package com.nkayyarath.dao.account;

import com.nkayyarath.model.Account;
import com.nkayyarath.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountDAOImpl implements AccountDAO {

    static PreparedStatement ps;
    Connection connection = null;
    //used an AccQuery.properties file for readability. Please refer to the file to read actual query.
    ResourceBundle rb = ResourceBundle.getBundle("com/nkayyarath/dao/account/AccQuery");

    public AccountDAOImpl() {
        try {
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addAccount(Account account) throws SQLException {
        final String sql = rb.getString("addAccount");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, account.getAccountNumber());
        ps.setInt(2, account.getCustomerID());
        ps.setDouble(3, account.getBalance());
        ps.setDouble(4, account.getOpeningBalance());
        ps.setDouble(5, account.getInterest());
        ps.setString(6, account.getAccountName());
        ps.setDate(7, account.getDateOpened());
        ps.setString(8, account.getAccountStatus());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Account was successfully created!");
        } else {
            System.out.println("Have you tried turning it on and off again?");
        }
    }

    /**
     * this updates account balance
     *
     * @param account
     * @throws SQLException
     */
    @Override
    public void updateAccount(Account account) throws SQLException {
        final String sql = rb.getString("updateAccount");
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, account.getBalance());
        ps.setInt(2, account.getAccountNumber());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Balance updated");
        } else {
            System.out.println("You might've gotten your account hacked...\nCall help desk ASAP!");
        }
    }

    @Override
    public void deleteAccount(int accountNumber) throws SQLException {
        final String sql = rb.getString("deleteAccount");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, accountNumber);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Account successfully deleted");
        } else {
            System.out.println("The Ninja's attacked our severs, please try again later...");
        }
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accountList = new ArrayList<>();
        final String sql = rb.getString("selectALL");

        ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Account account = new Account();
            account.setAccountName(rs.getString("account_name"));
            account.setAccountNumber(rs.getInt("account_number"));
            account.setBalance(rs.getDouble("balance"));
            account.setAccountStatus(rs.getString("account_status"));
            account.setOpeningBalance(rs.getDouble("opening_balance"));
            account.setCustomerID(rs.getInt("customer_id"));
            account.setDateOpened(rs.getDate("date_opened"));
            account.setInterest(rs.getDouble("interest"));
            accountList.add(account);
        }
        return accountList;
    }

    @Override
    public Account accountByNumber(int accountNumber) throws SQLException {
        final String sql = rb.getString("selectByAccountNumber");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, accountNumber);
        ResultSet rs = ps.executeQuery();
        Account account = new Account();

        if (rs.next()) {
            account.setAccountName(rs.getString("account_name"));
            account.setAccountNumber(rs.getInt("account_number"));
            account.setBalance(rs.getDouble("balance"));
            account.setAccountStatus(rs.getString("account_status"));
            account.setOpeningBalance(rs.getDouble("opening_balance"));
            account.setCustomerID(rs.getInt("customer_id"));
            account.setDateOpened(rs.getDate("date_opened"));
            account.setInterest(rs.getDouble("interest"));
        } else {
            System.out.println("L(・o・)」 you're not in our system.");
        }
        return account;
    }
}
