import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

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

    public void transactionHistory(String acctId) {

    }

    public boolean isAmountVaild(double amount){
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

    public void createAccount(String acctId, double startingBalance, String password) {
        try {
            json.writeAccountToJSON(new BankAccount(startingBalance, acctId, password, ""));
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal argument in the BankAccount constructor");
        }
    }

    public void closeAccount(String acctId) throws FileNotFoundException {
        File file = new File("src/main/resources/" + acctId + ".json");
        if(file.exists()){
            file.delete();
        }
        else {
            throw new FileNotFoundException("Account does not exist");
        }
    }

    //------------------ AdminAPI methods -------------------------//

    public double checkTotalAssets() {
        return 0;
    }

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

    public String[] getAccountIDs() {
        File IDFolder = new File("src/main/resources/");
        String[] files = IDFolder.list();
        for(int i = 0; i < files.length; i++) {
            files[i] = files[i].split("\\.")[0];
        }
        return files;
    }

}
