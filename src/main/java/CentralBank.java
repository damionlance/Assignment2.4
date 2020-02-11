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

    public String transactionHistory(String acctId) {
        return null;
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

    public static void main(String[] args) {
        CentralBank bank = new CentralBank();
        bank.createAccount("12345", 500);
        BankAccount account = json.readAccountFromJSON("12345");
    }

}
