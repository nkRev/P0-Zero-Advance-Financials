package com.nkayyarath.dao.transactions;

import com.nkayyarath.model.Transaction;
import com.nkayyarath.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionDAOImpl implements TransactionDAO {
    static PreparedStatement ps;
    Connection connection;
    ResourceBundle rb = ResourceBundle.getBundle("com/nkayyarath/dao/transactions/TransactQuery");

    public TransactionDAOImpl() {
        try {
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        final String sql = rb.getString("addTransaction");
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, transaction.getAmount());
        ps.setInt(2, transaction.getAccountNumber());
        ps.setDate(3, transaction.getDate());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Transaction completed.");
        } else {
            System.out.println("Transaction server was hacked.");
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) throws SQLException {
        final String sql = rb.getString("updateTransaction");
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, transaction.getAmount());
        ps.setDate(2, transaction.getDate());
        ps.setInt(3, transaction.getAccountNumber());

        int count = ps.getUpdateCount();
        if (count > 0) {
            System.out.println("Transaction updated.");
        } else {
            System.out.println("Something went wrong, please contact a support agent");
        }
    }

    @Override
    public void deleteTransaction(int accountNumber) throws SQLException {
        final String sql = rb.getString("deleteTransaction");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, accountNumber);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("Transaction deleted.");
        } else {
            System.out.println("Proceed to yell at developer when something goes wrong.");
        }

    }

    @Override
    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        final String sql = rb.getString("selectAllTransactions");
        ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(rs.getInt("account_number"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDate(rs.getDate("date"));

            transactionList.add(transaction);
        }

        return transactionList;
    }

    @Override
    public Transaction getTransactionByID(int accountNumber) throws SQLException {
        final String sql = rb.getString("selectByAccNumber");
        ps = connection.prepareStatement(sql);

        ps.setInt(1, accountNumber);
        ResultSet rs = ps.executeQuery();
        Transaction transaction = new Transaction();

        if (rs.next()) {
            transaction.setAccountNumber(rs.getInt("account_number"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDate(rs.getDate("date"));
        } else {
            System.out.println("Transaction not found.");
        }

        return transaction;
    }
}
