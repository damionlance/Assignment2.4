import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Admin implements AdminAPI{
    private List<String> frozenAccountsIDs;
    private String adminPassword;

    private List<BankAccount> accounts;
   // private ArrayList<Double> assets;//list of all assets for the user
    //TODO:CHANGE JSON TO NOT READ ACCOUNT ID
    public Admin( String adminPasswordIn){
        this.adminPassword =adminPasswordIn;
        this.frozenAccountsIDs =new ArrayList<>();//TODO:CHANGE
        this.accounts=new ArrayList<>();
    }
//TODO:ADD MAKE NEW ACCOUNT METHOD

    public void freezeAccount(String acctId) {
        String freezeAccount=null;
        BankAccount accountToFreeze = null;
        for(BankAccount bankAccounts:accounts){
            if(bankAccounts.getAcctId().equals(acctId)){
               freezeAccount=bankAccounts.getAcctId();
               accountToFreeze=bankAccounts;
            }
        }
        if(freezeAccount.equals(null)){
            throw new IllegalArgumentException("Account Id does not exist");

        }
        else{
            frozenAccountsIDs.add(freezeAccount);
            assert accountToFreeze != null;
            accountToFreeze.setAccountFrozen(true);
        }
    }

    public void unfreezeAcct(String acctId) {
        if(frozenAccountsIDs.contains(acctId)){
            for(BankAccount account:accounts){
                if(account.getAcctId().equals(acctId)){
                    account.setAccountFrozen(false);
                }
            }
        }
        else
            throw new IllegalArgumentException("Account does not exist");

    }

    public double getAssetTotal() {
        return 0;
    }

    public double calcTotalAssets() {
        return 0;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }
    public BankAccount findAccountByID(String accID){
        for(BankAccount account:accounts){
            if(account.getAcctId().equals(accID)){
                return account;
            }
        }
        return null;
    }
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

}
