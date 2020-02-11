import java.util.Collection;
import java.util.Scanner;

public class CentralBank implements AdvancedAPI, AdminAPI, BankAccount {

    //----------------- BasicAPI methods -------------------------//

    Scanner user_input = new Scanner( System.in );

    public boolean confirmCredentials(String acctId, String password) {

        String enteredAcctID = user_input.next();
        String enteredPassword = user_input.next();

        if (enteredAcctID.equals(acctId) && enteredPassword.equals(password)){

        }

        else{
            throw new IllegalArgumentException("ERROR: Incorrect account ID or password");
        }


    }

    public double checkBalance(String acctId) {
        return 0;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException {
        String amountString = Double.toString(Math.abs(amount));
        String[] splitter = amountString.toString().split("\\.");
        splitter[0].length();   // Before Decimal Count
        splitter[1].length();   // After  Decimal Count

        if (amount > balance) {
            //change to insufficientFundsException
            throw new RuntimeException("ERROR: You do not have enough funds to withdraw that amount.");
        } else if (isAmountValid(amount)) {
            balance -= amount;
            //append action to transaction history
        } else {
            throw new IllegalArgumentException("ERROR: Invalid withdraw amount");

        }


    }

    public void deposit(String acctId, double amount) {
        if (isAmountValid(amount)) {

            balance += amount;
        } else {
            throw new IllegalArgumentException("ERROR: Invalid deposit amount");
        }

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {
        if (isAmountValid(amount)) {
            double acct1 = acctIdToWithdrawFrom.balance;
            double acct2 = acctIdToDepositTo.balance;

            acct1 -= amount;
            acct2 += amount;

            acctIdToWithdrawFrom.balance = acct1;
            acctIdToDepositTo.balance = acct2;

            transactionHistory.append("Transfered " + amount + "from " + acctIdToWithdrawFrom + "to " + acctIdToDepositTo);
        } else {
            throw new InsufficientFundsException;
        }
    }

    public String transactionHistory(String acctId) {
        return null;
    }

    public boolean isAmountVaild(amount){
        String amountString = Double.toString(Math.abs(amount));
        String[] splitter = amountString.toString().split("\\.");
        splitter[0].length();   // Before Decimal Count
        splitter[1].length();   // After  Decimal Count

        if (amount <= 0){
            return false;
        }

        else if(splitter[1].length() > 2){
            return false;
        }

        else{
            return true;
        }
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance) {

    }

    public void closeAccount(String acctId) {

    }


    //------------------ AdminAPI methods -------------------------//

    public double checkTotalAssets() {
        return 0;
    }

    @Override
    public double calcTotalAssets() {
        return 0;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(String acctId) {

    }

    public void unfreezeAcct(String acctId) {

    }

}
