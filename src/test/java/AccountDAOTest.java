import com.nkayyarath.dao.account.AccountDAO;
import com.nkayyarath.dao.account.AccountDAOFactory;
import com.nkayyarath.model.Account;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AccountDAOTest {
    java.util.Date date;
    java.sql.Date sqlDate;

    @Test
    void addAccountTest() throws SQLException {

        Account account = new Account();
        account.setBalance(1100);
        account.setOpeningBalance(100);
        account.setAccountName("checkings");
        account.setCustomerID(1);
        account.setInterest(0);
        account.setAccountStatus("pending");

        date = new java.util.Date();
        sqlDate = new java.sql.Date(date.getTime());

        account.setDateOpened(sqlDate);

        AccountDAO accountDAO = AccountDAOFactory.getAccountDAO();
        accountDAO.addAccount(account);

    }

    void viewPendingAccountsTest() throws SQLException {
        Account account = new Account();
        account.setBalance(1100);
        account.setOpeningBalance(100);
        account.setAccountName("checkings");
        account.setCustomerID(1);
        account.setInterest(0);
        account.setAccountStatus("pending");

        AccountDAO accountDAO = AccountDAOFactory.getAccountDAO();

        accountDAO.viewPendingAccounts(account);
    }

    @Test
    void withdrawFromAccountTest() throws SQLException {
        AccountDAO accountDAO = AccountDAOFactory.getAccountDAO();
        accountDAO.withdrawFromAccount(6, 10);
    }

}
