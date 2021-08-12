package com.nkayyarath.dao.transactions;

import com.nkayyarath.model.Transaction;
import com.nkayyarath.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    static PreparedStatement ps;
    Connection connection = null;

    public TransactionDAOImpl() {
        try {
            this.connection = DAOUtilities.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        final String sql = "INSERT INTO transactions (amount, account_number, transaction_type, date) VALUES (?, ?, ?, ?)";
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, transaction.getAmount());
        ps.setInt(2, transaction.getAccountNumber());
        ps.setString(3, transaction.getTransactionType());
        ps.setDate(4, transaction.getDate());

        ps.executeUpdate();

    }

    @Override
    public void updateTransaction(Transaction transaction) throws SQLException {
        final String sql = "UPDATE transactions SET amount = ?, date = ? WHERE account_number = ?";
        ps = connection.prepareStatement(sql);

        ps.setDouble(1, transaction.getAmount());
        ps.setDate(2, transaction.getDate());
        ps.setInt(3, transaction.getAccountNumber());

        ps.getUpdateCount();

    }

    @Override
    public void deleteTransaction(int accountNumber) throws SQLException {
        final String sql = "DELETE FROM transactions WHERE account_number = ?";
        ps = connection.prepareStatement(sql);

        ps.setInt(1, accountNumber);

        ps.executeUpdate();

    }

    @Override
    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        final String sql = "call project0.sp_get_transactions() ";
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
        final String sql = "SELECT * FROM transactions WHERE account_number = ?";
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
