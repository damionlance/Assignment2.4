import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
        return 0;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

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

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(String acctId) {

    }

    public void unfreezeAcct(String acctId) {

    }

}
