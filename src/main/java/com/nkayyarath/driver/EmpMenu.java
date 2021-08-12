package com.nkayyarath.driver;

import com.nkayyarath.dao.account.AccountDAO;
import com.nkayyarath.dao.account.AccountDAOImpl;
import com.nkayyarath.dao.customer.CustomerDAO;
import com.nkayyarath.dao.customer.CustomerDAOImpl;

import com.nkayyarath.dao.transactions.TransactionDAO;
import com.nkayyarath.dao.transactions.TransactionDAOImpl;
import com.nkayyarath.display.EmployeeMenu;
import com.nkayyarath.model.Account;
import com.nkayyarath.model.Customer;
import com.nkayyarath.model.Transaction;

import java.sql.SQLException;
import java.util.List;


import static com.nkayyarath.driver.Main.in;
import static com.nkayyarath.driver.Main.menuLog;

public class EmpMenu {

    public static void employeeMenu() throws SQLException {
        Customer customer;
        CustomerDAO customerDAO = new CustomerDAOImpl();

        Transaction transaction;
        TransactionDAO transactionDAO = new TransactionDAOImpl();

        Account account;
        AccountDAO accountDAO = new AccountDAOImpl();

        EmployeeMenu menu = new EmployeeMenu();

        //for realtime date
        java.util.Date date;
        java.sql.Date sqlDate;

        boolean finished = false;
        int input;
        menuLog.info("Reached Employee Menu");
        do {
            menu.display();
            System.out.print(">> ");
            input = in.nextInt();

            switch (input) {
                //approve or reject an account
                case 1:
                    menuLog.info("Viewing Pending Accounts");
                    System.out.println("Listing pending accounts");
                    Account pAccount = new Account();
                    pAccount.setAccountStatus("pending");
                    List<Account> pendingAccounts;
                    pendingAccounts = accountDAO.viewPendingAccounts(pAccount);

                    System.out.println("*****************************");
                    for (Account pending : pendingAccounts) {
                        System.out.println(pending);
                    }
                    System.out.println("*****************************");
                    account = new Account();

                    System.out.println("Customer ID: ");
                    int cust_id = in.nextInt();
                    account.setCustomerID(cust_id);

                    System.out.println("Account Number: ");
                    int accountNumber = in.nextInt();
                    account.setAccountNumber(accountNumber);

                    System.out.println("Approve or Reject?");
                    String accStatus = in.next().toLowerCase();

                    if (accStatus.equals("approve")) {
                        accStatus = "approved";
                        account.setAccountStatus(accStatus);
                        accountDAO.acceptOrRejectPendingAcc(account);
                    } else if (accStatus.equals("reject")) {
                        accStatus = "rejected";
                        account.setAccountStatus(accStatus);
                        accountDAO.acceptOrRejectPendingAcc(account);
                    } else {
                        System.out.println("|_(⊙＿⊙'')_| try again...");
                    }

                    break;

                //view customer's accounts
                case 2:
                    menuLog.info("Viewing customer account");
                    System.out.println("Customer ID: ");
                    cust_id = in.nextInt();

                    account = new Account();
                    account.setCustomerID(cust_id);

                    List<Account> accountList;
                    accountList = accountDAO.getAllAccounts(account);
                    for (Account a : accountList) {
                        System.out.println(a);
                    }
                    break;

                //view all transactions
                case 3:
                    menuLog.info("viewing all transactions");
                    System.out.println("*****************************************");
                    System.out.println("Printing all transactions...");
                    System.out.println();

                    List<Transaction> transactionList;
                    transactionList = transactionDAO.getAllTransactions();
                    for (Transaction t : transactionList) {
                        System.out.println(t);
                    }
                    break;
                case 0:
                    menuLog.info("Employee Logout");
                    LandingMenu mainMenu = new LandingMenu();
                    System.out.println("Logging out");
                    finished = true;
                    mainMenu.mainMenu();
                    break;
            }
        } while (!finished);


    }
}
