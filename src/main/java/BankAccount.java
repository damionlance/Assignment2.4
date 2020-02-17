public class BankAccount {

    private String acctId;
    private String password;
    private String transactionHistory;
    private double balance;


    private boolean accountFrozen=false;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(double startingBalance, String acctId, String password, String transactionHistory){

        if (isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Amount: " + startingBalance + " is invalid");
        }
//        if (isEmailValid(email)){
//            this.email = email;
//        }
//        else {
//            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
//        }
        // todo: Add JUnit tests to these, and make them actually check for validity
        this.acctId = acctId;
        this.password = password;
        this.transactionHistory = transactionHistory;
    }

    public double getBalance(){
        return balance;
    }

//    public String getEmail(){ return email; }


    public String getPassword() { return password; }

    public String getAcctId() { return acctId; }

    public String getTransactionHistory() { return transactionHistory; }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if amount is non-negative or argument has more precision than to the hundredths place
     * @throws InsufficientFundsException if amount is bigger than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException {
        if (!accountFrozen) {
            if (amount > balance) { // Cannot withdraw an amount more than the balance
                throw new InsufficientFundsException("Too small a balance");
            }
            if (amount < 0) { // Cannot withdraw an amount less than 0
                throw new IllegalArgumentException("Cannot withdraw a negative amount");
            }
            String amountString = "" + amount;
            if (amountString.indexOf(".") != -1) {
                String places = amountString.substring(amountString.indexOf("."), amountString.length() - 1);
                if (places.length() > 2) {
                    throw new IllegalArgumentException("Cannot have more than 0.01 precision");
                }
            }
            balance -= amount;
            balance = (double) Math.round(balance * 100.0) / 100.0;
        }
        else{
            throw new IllegalArgumentException("Account is frozen");
        }
    }

    public void updateTransactionHistory (String statement){
        //fix dis
        transactionHistory = transactionHistory + statement;
    }


    public static boolean isEmailValid(String email){
        if (email.length() == 0){
            return false;
        }
        if (email.indexOf('@') <= 0 || email.indexOf('@') != email.lastIndexOf('@')){
            return false;
        }else if (email.indexOf("-@") != -1){
            return false;
        }else if (email.indexOf("..") != -1){
            return false;
        }else if (email.indexOf(".") <= 0 || email.length() - email.lastIndexOf(".") < 3){
            return false;
        }else if (email.indexOf("#") != -1){
            return false;
        }
        int item;
        for(int i = 0; i < email.length(); i++){
            item = (int) email.charAt(i);
            if (item <= 44){
                return false;
            }else if(item > 122){
                return false;
            }else if(!(item == 45 || item == 46 || item == 95 || item == 64 || (item >= 97 && item < 123) || (item >= 65 && item < 91))){
                return false;
            }
        }
        return true;
    }
    /**
    * Makes sure amount is a valid monetary amount. (No more than 2 decimal places and is positive)
    **/
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

    /**
     * adds money to a bank account
     * @param depositAmount
     * @throws IllegalArgumentException if amount invalid
     */
    public void deposit(double depositAmount){
        if(!accountFrozen) {
            if (isAmountValid(depositAmount)) {
                balance += depositAmount;
            } else {
                throw new IllegalArgumentException("");
            }
        }
        else{
            throw new IllegalArgumentException("Account is frozen");
        }
    }


    public void setAccountFrozen(boolean accountFrozen) {
        this.accountFrozen = accountFrozen;
    }

}
