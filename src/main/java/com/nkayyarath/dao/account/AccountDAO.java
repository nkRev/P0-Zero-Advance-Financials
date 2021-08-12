package com.nkayyarath.dao.account;

import com.nkayyarath.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    void addAccount(Account account) throws SQLException;

    void setTransferStatus(Account account1) throws SQLException;

    List<Account> getTransferStatus(Account account1) throws SQLException;

    void depositToAccount(int accountNumber, double amt) throws SQLException;

    void withdrawFromAccount(int accountNumber, double amt) throws SQLException;

    List<Account> viewPendingAccounts(Account account) throws SQLException;

    void acceptOrRejectPendingAcc(Account account) throws SQLException;

    void deleteAccount(int accountNumber) throws SQLException;

    List<Account> getAllAccounts(Account account) throws SQLException;

    Account accountByNumber(int accountNumber) throws SQLException;

    Account accountByCustomerId(int customer_ID) throws SQLException;

}
