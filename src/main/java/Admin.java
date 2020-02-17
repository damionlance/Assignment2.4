import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Admin implements AdminAPI{
    private List<String> frozenAccountsIDs;
    private String adminPassword;//TODO:CREATE PASSWORD?
    private List<BankAccount> accounts;

    public Admin( String adminPasswordIn){
        this.adminPassword =adminPasswordIn;
        this.frozenAccountsIDs =new ArrayList<>();
        this.accounts=new ArrayList<>();
    }

    /**
     * Freezes an account by the account id so the bank account user cannot make any transactions
     * @param acctId ID of the account that needs be frozen
     */
    public void freezeAccount(String acctId) {
        BankAccount accountToFreeze = findAccountByID(accounts, acctId);
        if (accountToFreeze !=null) {
            frozenAccountsIDs.add(accountToFreeze.getAcctId());
            accountToFreeze.setAccountFrozen(true);
            }
        else
            throw new IllegalArgumentException("Account Id does not exist");
    }

    /**
     * Unfreezes an account that has been frozen
     * @param acctId ID of the account that is frozen
     */
    public void unfreezeAcct(String acctId) {
        if(frozenAccountsIDs.contains(acctId)){
           BankAccount account=findAccountByID(accounts,acctId);
           account.setAccountFrozen(false);
        }
        else
            throw new IllegalArgumentException("Account does not exist");
    }

    /**
     * Helper functions to find bank accounts by their IDs
     * @param accounts the current list of bank accounts
     * @param accID ID of the bank account that needs to be found
     * @return a bank account
     */
    public static BankAccount findAccountByID(List<BankAccount> accounts,String accID){
        for(BankAccount account:accounts){
            if(account.getAcctId().equals(accID)){
                return account;
            }
        }return null;
    }

    public double calcTotalAssets() {
        double totalBankAssets=0;
        if(accounts!=null){
            for(BankAccount bankAccount:accounts){
                totalBankAssets+=bankAccount.getBalance();
            }
        }
        return totalBankAssets;
    }


    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }



    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

}
 /* public double getAssetTotal() {

        return calcTotalAssets(accounts);
    }

    public double calcTotalAssets(List<BankAccount> accounts) {
        double totalBankAssets=0;
        if(accounts!=null){
            for(BankAccount bankAccount:accounts){
                totalBankAssets+=bankAccount.getBalance();
            }
        }
        return totalBankAssets;
    }*/