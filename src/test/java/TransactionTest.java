import com.nkayyarath.dao.transactions.TransactionDAO;
import com.nkayyarath.dao.transactions.TransactionDAOFactory;
import com.nkayyarath.model.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TransactionTest {
    @Test
    void getTransactionsTest() throws SQLException {
        TransactionDAO transactionDAO = TransactionDAOFactory.getTransactionDAO();
        transactionDAO.getAllTransactions();
    }



}
