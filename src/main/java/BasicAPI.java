import org.json.simple.parser.ParseException;

import java.io.IOException;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password) throws IOException, ParseException;

    double checkBalance(String acctId) throws IOException, ParseException;

    void withdraw(String acctId, double amount) throws InsufficientFundsException, IOException, ParseException;

    void deposit(String acctId, double amount) throws IOException, ParseException;

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, IOException, ParseException;

    void transactionHistory(String acctId) throws IOException, ParseException;

}
