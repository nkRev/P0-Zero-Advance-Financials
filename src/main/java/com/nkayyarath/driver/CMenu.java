package com.nkayyarath.driver;

import com.nkayyarath.dao.account.AccountDAO;
import com.nkayyarath.dao.account.AccountDAOFactory;
import com.nkayyarath.dao.customer.CustomerDAO;
import com.nkayyarath.dao.customer.CustomerDAOFactory;
import com.nkayyarath.dao.transactions.TransactionDAO;
import com.nkayyarath.dao.transactions.TransactionDAOFactory;
import com.nkayyarath.display.CustomerMenu;
import com.nkayyarath.model.Account;
import com.nkayyarath.model.Customer;
import com.nkayyarath.model.Transaction;


import java.sql.SQLException;


import static com.nkayyarath.driver.Main.in;
import static com.nkayyarath.driver.Main.menuLog;

public class CMenu {


    public static void customerMenu() throws SQLException {
        CustomerMenu menu = new CustomerMenu();

        Customer customer = new Customer();
        CustomerDAO customerDAO = CustomerDAOFactory.getCustomerDAO();

        Transaction transaction;
        TransactionDAO transactionDAO = TransactionDAOFactory.getTransactionDAO();

        AccountDAO accountDAO = AccountDAOFactory.getAccountDAO();
        Account account;

        //for realtime date
        java.util.Date date;
        java.sql.Date sqlDate;

        boolean finished = false;
        int input;

        menuLog.info("Reached Customer Menu");
        do {
            menu.display();
            System.out.println(">> ");
            input = in.nextInt();
            switch (input) {
                //deposit
                case 1:
                    menuLog.info("Making a deposit");
                    System.out.println("Specify account number for deposit ");
                    int accountNumber = in.nextInt();

                    System.out.println("Deposit amount: ");
                    double depositAmt = in.nextDouble();
                    //get date
                    date = new java.util.Date();
                    sqlDate = new java.sql.Date(date.getTime());

                    //create transaction obj
                    transaction = new Transaction();
                    transaction.setAccountNumber(accountNumber);
                    transaction.setAmount(depositAmt);
                    transaction.setTransactionType("Deposit");
                    transaction.setDate(sqlDate);

                    //create account (deposit) obj

                    accountDAO.depositToAccount(accountNumber, depositAmt);
                    transactionDAO.addTransaction(transaction);
                    break;

                //withdraw
                case 2:
                    menuLog.info("Making a withdraw");
                    System.out.println("Please specify account name: ");
                    String accountName = in.next();

                    System.out.println("Account number: ");
                    accountNumber = in.nextInt();

                    System.out.println("Amount: ");
                    double withdrawAmt = in.nextDouble();

                    date = new java.util.Date();
                    sqlDate = new java.sql.Date(date.getTime());

                    account = new Account();
                    account.setAccountName(accountName);
                    account.setAccountNumber(accountNumber);


                    transaction = new Transaction();
                    transaction.setDate(sqlDate);
                    transaction.setAmount(withdrawAmt);
                    transaction.setAccountNumber(accountNumber);
                    transaction.setTransactionType("Withdraw");

                    Account account1 = accountDAO.accountByNumber(accountNumber);
                    if (withdrawAmt < account1.getBalance()) {
                        account.setWithdrawAmt(withdrawAmt);
                        accountDAO.withdrawFromAccount(accountNumber, withdrawAmt);
                        transactionDAO.addTransaction(transaction);
                    } else {
                        System.out.println("The withdraw of $" + withdrawAmt + " will result in a negative balance");
                    }

                    break;

                //Money Transfer/deposit to account
                case 3:
                    menuLog.info("Making a money transfer");
                    System.out.println("Please specify account name: ");
                    accountName = in.next();

                    System.out.println("Your account number: ");
                    accountNumber = in.nextInt();

                    System.out.println("Amount to transfer: ");
                    withdrawAmt = in.nextDouble();

                    System.out.println("Specify account number for Transfer ");
                    int tAccountNumber = in.nextInt();

                    System.out.println("Account Name: ");
                    String tAccountName = in.next();

                    System.out.println("Deposit amount: ");
                    double tDepositAmt = in.nextDouble();

                    account = new Account();
                    account.setAccountNumber(accountNumber);
                    account.setAccountName(accountName);
                    account.setWithdrawAmt(withdrawAmt);
                    account.setTransferStatus("pending");
                    accountDAO.setTransferStatus(account);

                    //create account (deposit) obj
                    Account taccount = new Account();
                    taccount.setBalance(tDepositAmt);
                    taccount.setAccountName(tAccountName);
                    taccount.setAccountNumber(tAccountNumber);
                    taccount.setTransferStatus("pending");
                    accountDAO.setTransferStatus(taccount);

                    if (taccount.getTransferStatus().equals(account.getTransferStatus())) {
                        accountDAO.withdrawFromAccount(accountNumber, withdrawAmt);
                        accountDAO.depositToAccount(tAccountNumber, tDepositAmt);
                        //get date
                        date = new java.util.Date();
                        sqlDate = new java.sql.Date(date.getTime());

                        transaction = new Transaction();
                        transaction.setAccountNumber(accountNumber);
                        transaction.setAmount(withdrawAmt);
                        transaction.setTransactionType("Transfer");
                        transaction.setDate(sqlDate);

                        transactionDAO.addTransaction(transaction);

                        //create transaction obj
                        date = new java.util.Date();
                        sqlDate = new java.sql.Date(date.getTime());

                        transaction = new Transaction();
                        transaction.setAccountNumber(tAccountNumber);
                        transaction.setAmount(tDepositAmt);
                        transaction.setTransactionType("Transfer");
                        transaction.setDate(sqlDate);

                        transactionDAO.addTransaction(transaction);

                        //revert transfer_status
                        account.setTransferStatus("complete");
                        taccount.setTransferStatus("complete");
                        accountDAO.setTransferStatus(account);
                        accountDAO.setTransferStatus(taccount);
                    } else {
                        System.out.println("Money transfer could not be completed please try again");
                        date = new java.util.Date();
                        sqlDate = new java.sql.Date(date.getTime());

                        transaction = new Transaction();
                        transaction.setAccountNumber(accountNumber);
                        transaction.setAccountNumber(accountNumber);
                        transaction.setTransactionType("Failed Transfer");
                        transaction.setDate(sqlDate);
                    }

                    break;


                //apply for new bank account
                case 4:
                    menuLog.info("Bank Account Creation Service");

                    System.out.println("Name of account: ");
                    accountName = in.next().toLowerCase();

                    System.out.println("Opening deposit amount: ");
                    double openingBal = in.nextDouble();

                    System.out.println("Customer ID: ");
                    int customerID = in.nextInt();

                    double interestRate;
                    if (accountName.equals("savings")) {
                        System.out.println("your interest rate is: 0.5APY");
                        interestRate = 0.5;
                    } else {
                        interestRate = 0;
                    }

                    double balance = openingBal;

                    String accStatus;
                    if (openingBal > 50) {
                        accStatus = "approved";
                    } else {
                        accStatus = "pending";
                    }

                    date = new java.util.Date();
                    sqlDate = new java.sql.Date(date.getTime());


                    account = new Account();
                    account.setCustomerID(customerID);
                    account.setOpeningBalance(openingBal);
                    account.setBalance(balance);
                    account.setAccountStatus(accStatus.toLowerCase());
                    account.setAccountName(accountName);
                    account.setInterest(interestRate);
                    account.setDateOpened(sqlDate);

                    accountDAO.addAccount(account);

                    Account a = accountDAO.accountByCustomerId(customerID);

                    System.out.println("PLEASE KEEP ACCOUNT NUMBER SAFE: " + a.getAccountNumber());


                    Account account2;
                    account2 = accountDAO.accountByCustomerId(customerID);

                    transaction = new Transaction();
                    transaction.setAccountNumber(account2.getAccountNumber());
                    transaction.setTransactionType("New " + accountName + "'s" + " Account");
                    transaction.setDate(sqlDate);

                    break;
                ///view your balance
                case 5:
                    menuLog.info("Viewing balance");
                    System.out.println("Account number: ");
                    accountNumber = in.nextInt();
                    account = accountDAO.accountByNumber(accountNumber);
                    System.out.println("Your balance is: " + account.getBalance());
                    break;

                //go back to main menu
                case 0:
                    menuLog.info("Logging out");
                    LandingMenu mainMenu = new LandingMenu();
                    finished = true;
                    mainMenu.mainMenu();
                    break;


            }
        } while (!finished);
    }
}
