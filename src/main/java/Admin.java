import java.util.ArrayList;
import java.util.Collection;

public class Admin implements AdminAPI{
    private ArrayList<String> frozenAccounts;
    private String adminPassword;
    private ArrayList<BankAccount> accounts;
   // private ArrayList<Double> assets;//list of all assets for the user
    //TODO:CHANGE JSON TO NOT READ ACCOUNT ID
    public Admin(String accountEmailIn, String adminPasswordIn, String accountIDIn){
        this.adminPassword =adminPasswordIn;
    }
//TODO:ADD MAKE NEW ACCOUNT METHOD
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
