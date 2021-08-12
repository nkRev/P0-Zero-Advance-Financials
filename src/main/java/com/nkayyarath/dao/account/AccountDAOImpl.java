package com.nkayyarath.dao.account;

import com.nkayyarath.model.Account;
import com.nkayyarath.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    static PreparedStatement ps;
    Connection connection = null;

    public AccountDAOImpl() {
        try {
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addAccount(Account account) throws SQLException {
        final String sql = "INSERT INTO accounts"
                + " (balance, opening_balance, account_name, customer_id, interest, account_status, date_opened, transfer_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, account.getBalance());
        ps.setDouble(2, account.getOpeningBalance());
        ps.setString(3, account.getAccountName());
        ps.setInt(4, account.getCustomerID());
        ps.setDouble(5, account.getInterest());
        ps.setString(6, account.getAccountStatus());
        ps.setDate(7, account.getDateOpened());
        ps.setString(8, account.getTransferStatus());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Account was successfully created!");
        } else {
            System.out.println("Have you tried turning it on and off again?");
        }
    }

    @Override
    public void setTransferStatus(Account account) throws SQLException {
        final String sql = "UPDATE accounts SET transfer_status = ? WHERE account_number = ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, account.getTransferStatus());
        ps.setInt(2, account.getAccountNumber());
        ps.executeUpdate();

    }

    @Override
    public List<Account> getTransferStatus(Account account1) throws SQLException {
        final String sql = "Select * from accounts where transfer_status = ? and account_number =?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, "pending");
        ps.setInt(2, account1.getAccountNumber());

        ResultSet rs  = ps.executeQuery();
        List<Account> transferStatusList = new ArrayList<>();
        while(rs.next()){
            account1.setAccountName(rs.getString("account_name"));
            account1.setAccountNumber(rs.getInt("account_number"));
            account1.setBalance(rs.getDouble("balance"));
            account1.setAccountStatus(rs.getString("account_status"));
            account1.setOpeningBalance(rs.getDouble("opening_balance"));
            account1.setCustomerID(rs.getInt("customer_id"));
            account1.setDateOpened(rs.getDate("date_opened"));
            account1.setInterest(rs.getDouble("interest"));
            account1.setTransferStatus(rs.getString("transfer_status"));
            transferStatusList.add(account1);
        }
        return transferStatusList;
    }


    @Override
    public void depositToAccount(int accountNumber, double amt) throws SQLException {
        final String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, amt);
        ps.setInt(2, accountNumber);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Deposited");
        } else {
            System.out.println("Go yell at the bank!");
        }
    }

    @Override
    public void withdrawFromAccount(int accountNumber, double amt) throws SQLException {
        final String sqlUpdate = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        ps = connection.prepareStatement(sqlUpdate);
        AccountDAO accountDAO = AccountDAOFactory.getAccountDAO();

            ps.setDouble(1, amt);
            ps.setInt(2, accountNumber);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(amt + " Withdrawn");
            } else {
                System.out.println("Blame the guy who coded the system.");
            }

    }

    @Override
    public List<Account> viewPendingAccounts(Account account) throws SQLException {
        final String sql = "Select * FROM accounts where account_status = ?";
        ps = connection.prepareStatement(sql);
        account.setAccountStatus("pending");
        ps.setString(1, account.getAccountStatus());

        List<Account> accountList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            account = new Account();
            account.setAccountName(rs.getString("account_name"));
            account.setAccountNumber(rs.getInt("account_number"));
            account.setBalance(rs.getDouble("balance"));
            account.setAccountStatus(rs.getString("account_status"));
            account.setOpeningBalance(rs.getDouble("opening_balance"));
            account.setCustomerID(rs.getInt("customer_id"));
            account.setDateOpened(rs.getDate("date_opened"));
            account.setInterest(rs.getDouble("interest"));
            account.setTransferStatus(rs.getString("transfer_status"));
            accountList.add(account);
        }
        return accountList;

    }

    @Override
    public void acceptOrRejectPendingAcc(Account account) throws SQLException {
        final String sql = "UPDATE accounts SET account_status = ? where customer_id = ? AND account_number = ?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, account.getAccountStatus());
        ps.setInt(2, account.getCustomerID());
        ps.setInt(3, account.getAccountNumber());
        int count = ps.executeUpdate();
        if (count > 0) System.out.println("Task completed");

    }

    @Override
    public void deleteAccount(int accountNumber) throws SQLException {
        final String sql = "DELETE FROM accounts WHERE account_number = ?";
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
    public List<Account> getAllAccounts(Account account) throws SQLException {
        List<Account> accountList = new ArrayList<>();
        final String sql = "SELECT * FROM accounts WHERE customer_id = ?";
        ps = connection.prepareStatement(sql);


        ps.setInt(1, account.getCustomerID());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            account = new Account();
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
        final String sql = "SELECT * FROM accounts WHERE account_number = ?";
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

    @Override
    public Account accountByCustomerId(int customer_ID) throws SQLException {
        final String sql = "SELECT *  FROM accounts where customer_id = ?";
        ps = connection.prepareStatement(sql);

        ps.setInt(1, customer_ID);
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
            account.setTransferStatus(rs.getString("transfer_status"));
        } else {
            System.out.println("L(・o・)」 you're not in our system.");
        }
        return account;
    }
}
