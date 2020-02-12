import java.io.File;
import java.util.Collection;
import java.util.Scanner;

public class CentralBank implements AdvancedAPI, AdminAPI {

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        //just comparing on file accountID and password

        BankAccount returnedAccount = json.readAccountFromJSON(acctId);

        String acctIDonFile = returnedAccount.getAcctId();
        String passwordOnFile = returnedAccount.getPassword();

        if(acctIDonFile.equals(acctId) && passwordOnFile.equals(password)){

            return true;
        }
        else{
            return false;
        }
    }

    public double checkBalance(String acctId) {

        BankAccount returnedAccount = json.readAccountFromJSON(acctId);

        double accountBalance = returnedAccount.getBalance();

        String checkBalanceUpdate = "Checked balance. Balance at " + accountBalance + "\n";
        returnedAccount.updateTransactionHistory(checkBalanceUpdate);

        return accountBalance;

    }

    public void withdraw(String acctId, double amount) throws IllegalArgumentException {
        String amountString = Double.toString(Math.abs(amount));
        String[] splitter = amountString.toString().split("\\.");
        splitter[0].length();   // Before Decimal Count
        splitter[1].length();   // After  Decimal Count

        BankAccount returnedAccount = json.readAccountFromJSON(acctId);
        double balance = returnedAccount.getBalance();

        if (amount > balance) {
            //change to insufficientFundsException
            throw new RuntimeException("ERROR: You do not have enough funds to withdraw that amount.");
        } else if (isAmountValid(amount)) {
            returnedAccount.withdraw(amount);
            //append action to transaction history
            String withdrawNotice = "Withdrew " + amount + " from account " + acctId + "\n";
            returnedAccount.updateTransactionHistory(withdrawNotice);
        } else {
            throw new IllegalArgumentException("ERROR: Invalid withdraw amount");

        }

    }

    public void deposit(String acctId, double amount) {
        if (isAmountValid(amount)) {
            BankAccount returnedAccount = json.readAccountFromJSON(acctId);
            returnedAccount.deposit(amount);

            String depositNotice = "Deposited " + amount + " to " + acctId + "\n";

            returnedAccount.updateTransactionHistory(depositNotice);
        } else {
            throw new IllegalArgumentException("ERROR: Invalid deposit amount");
        }

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {
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
            throw new RuntimeException();
        }
    }

    public String transactionHistory(String acctId) {

        BankAccount returnedAccount = json.readAccountFromJSON(acctId);

        String transHistory = returnedAccount.getTransactionHistory();

        return transHistory;
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

    public void createAccount(String acctId, double startingBalance) {

        Scanner in = new Scanner(System.in);
        System.out.println("What email should be attached to this account: ");
        String emailResponse = in.nextLine();
        System.out.println("What password would you like: ");
        String password = in.nextLine();

        try {
            json.writeAccountToJSON(new BankAccount(emailResponse, startingBalance, acctId, password));
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid field... Try again");
        }
    }

    public void closeAccount(String acctId) {
        try {
            File file = new File("src/main/resources/" + acctId + ".json");
            file.delete();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(String acctId) {

    }


    //------------------ AdminAPI methods -------------------------//

    public double checkTotalAssets() {
        return 0;
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 8fae3c11e7042c21b879993386c10383a839ca36
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

    public static void main(String[] args) {
        CentralBank bank = new CentralBank();
        bank.createAccount("12345", 500);
        BankAccount account = json.readAccountFromJSON("12345");
    }

}
