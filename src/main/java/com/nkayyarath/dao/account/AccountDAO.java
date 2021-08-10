package com.nkayyarath.dao.account;

import com.nkayyarath.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    void addAccount(Account account) throws SQLException;

    void updateAccount(Account account) throws SQLException;

    void deleteAccount(int accountNumber) throws SQLException;

    List<Account> getAllAccounts() throws SQLException;

    Account accountByNumber(int accountNumber) throws SQLException;

}
