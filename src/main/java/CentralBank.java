import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) throws IOException, ParseException {

        //create bankaccount based on information from JSON
        BankAccount returnedAccount = json.readAccountFromJSON(acctId);

        //string containing returnedAccount's account ID
        String acctIDonFile = returnedAccount.getAcctId();

        //string containing returnedAccount's password
        String passwordOnFile = returnedAccount.getPassword();

        //make sure acctID and password match each other in the JSON
        if(acctIDonFile.equals(acctId) && passwordOnFile.equals(password)){

            return true;
        }
        else{
            return false;
        }
    }

    public double checkBalance(String acctId) throws IOException, ParseException {

        //copy account information from JSON based on accountID
        BankAccount returnedAccount = json.readAccountFromJSON(acctId);

        //variable for balance of returnedAccount
        double accountBalance = returnedAccount.getBalance();

        //updating
        String checkBalanceUpdate = "Checked balance. Balance at " + accountBalance + "\n";
        returnedAccount.updateTransactionHistory(checkBalanceUpdate);

        return accountBalance;

    }

    public void withdraw(String acctId, double amount) throws IllegalArgumentException, IOException, ParseException, InsufficientFundsException {

        String amountString = Double.toString(Math.abs(amount));
        String[] splitter = amountString.toString().split("\\.");
        splitter[0].length();   // Before Decimal Count
        splitter[1].length();   // After  Decimal Count

        BankAccount returnedAccount = json.readAccountFromJSON(acctId);
        double balance = returnedAccount.getBalance();

        if (amount > balance) {
            //change to insufficientFundsException
            throw new InsufficientFundsException("ERROR: You do not have enough funds to withdraw that amount.");
        } else if (isAmountValid(amount)) {
            returnedAccount.withdraw(amount);
            //append action to transaction history
            String withdrawNotice = "Withdrew " + amount + " from account " + acctId + "\n";
            returnedAccount.updateTransactionHistory(withdrawNotice);
        } else {
            throw new InsufficientFundsException("ERROR: Invalid withdraw amount");

        }


    }

    public void deposit(String acctId, double amount) throws IOException, ParseException {
        if (isAmountValid(amount)) {
            BankAccount returnedAccount = json.readAccountFromJSON(acctId);
            returnedAccount.deposit(amount);

            String depositNotice = "Deposited " + amount + " to " + acctId + "\n";

            returnedAccount.updateTransactionHistory(depositNotice);
        } else {
            throw new IllegalArgumentException("ERROR: Invalid deposit amount");
        }

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, IOException, ParseException {
        if (isAmountValid(amount)) {

            BankAccount giveAccount = json.readAccountFromJSON(acctIdToWithdrawFrom);
            BankAccount getAccount = json.readAccountFromJSON(acctIdToDepositTo);

            double giveBalance = giveAccount.getBalance();

            if (amount <= giveBalance) {

                giveAccount.withdraw(amount);
                getAccount.deposit(amount);

                String history = giveAccount.getTransactionHistory();

                String sendTransferNotice = "Transferred $" + amount + " from " + acctIdToWithdrawFrom + " to "
                        + acctIdToDepositTo + "\n";
                giveAccount.updateTransactionHistory(sendTransferNotice);

                String recieveTransferNotice = "Received $" + amount + " from " + acctIdToWithdrawFrom + "\n";
                getAccount.updateTransactionHistory(recieveTransferNotice);
            }
        } else {
            //fix dis
            throw new InsufficientFundsException("ERROR: Invalid withdraw amount");
        }
    }

    public void transactionHistory(String acctId) throws IOException, ParseException {

        BankAccount returnedAccount = json.readAccountFromJSON(acctId);

        String transHistory = (String) returnedAccount.getTransactionHistory();

    }

    public static boolean isAmountValid(double checkNum){
        if(checkNum <= 0){
            return false;
        }
        String checkNumStr = "" + checkNum;
        String[] delimitedStr = checkNumStr.split(".");
        int indexOfDot = checkNumStr.indexOf('.');
        if(indexOfDot == -1){
            return true;
        }
        else if(checkNumStr.length() - indexOfDot - 1 > 2){
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
