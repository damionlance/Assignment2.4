import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class ATMUI{

    CentralBank myBank;

    public ATMUI(){

    }
    public void run() throws IOException, ParseException, InsufficientFundsException {
        myBank = CentralBank.testBank();
        boolean confirmedCreds = false;
        boolean action = false;
        while(!action) {
            String loginInfo = null;
            try {
                loginInfo = login();
                Boolean.parseBoolean(loginInfo.split(",")[1]);
                confirmedCreds = Boolean.parseBoolean(loginInfo.split(",")[1]);
            } catch (IOException e) {
                System.out.println("Invalid credentials, try again...");
                break;
            } catch (ParseException e) {
                System.out.println("Invalid credentials, try again...");
                break;
            }
            if (confirmedCreds) {
                if(json.readAccountFromJSON(loginInfo.split(",")[0]).isFrozen()){
                    System.out.println("Account frozen... Try again later");
                    break;
                } else{
                    action = true;
                    takeAction(json.readAccountFromJSON(loginInfo.split(",")[0]));
                }
            } else {
                System.out.println("Invalid credentials, try again...");
                break;
            }
        }

    }
    private String login() throws IOException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your account ID: ");
        String acctId = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        if (myBank.confirmCredentials(acctId, password)) {
            return "" + acctId + ",true";
        }
        else{
            return "" + acctId + ",false";
        }
    }
    private void takeAction(BankAccount account) throws InsufficientFundsException {
        System.out.println("Enter your action:");
        System.out.println("1: deposit");
        System.out.println("2: withdraw");
        System.out.println("3: transfer");
        System.out.println("4: logout");
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        if(action.equals("1")){
            System.out.println("Enter your amount to deposit: ");
            String depositedAmount = in.nextLine();
            double depositedAmountDouble = Double.parseDouble(depositedAmount);
            account.deposit(depositedAmountDouble);
            json.writeAccountToJSON(account);
            System.out.println("Deposit successful!");
        }
        else if(action.equals("2")){
            System.out.println("Enter your amount to withdraw: ");
            String withdrawnAmount = in.nextLine();
            double withdrawnAmountDouble = Double.parseDouble(withdrawnAmount);
            try {
                account.withdraw(withdrawnAmountDouble);
                json.writeAccountToJSON(account);
            } catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds...");
                return;
            }
            System.out.println("Withdraw successful!");
        }else if(action.equals("3")){
            System.out.println("Enter an account ID to transfer to: ");
            String acctIdToTransfer = in.nextLine();
            System.out.println("Enter amount to transfer: ");
            String amount = in.nextLine();
            try{
                myBank.transfer(account.getAcctId(), acctIdToTransfer, Double.parseDouble(amount));
            } catch (ParseException e) {
                System.out.println("Invalid parameters...");
                return;
            } catch (IOException e) {
                System.out.println("Invalid parameters...");
                return;
            }
            System.out.println("Transfer successful!");
        }else if(action.equals("4")){
            System.out.println("Logout successful!");
            return;
        }else{
            System.out.println("Invalid action...");
        }
    }
    public static void main(String[] args) throws IOException, ParseException, InsufficientFundsException {
        ATMUI myATM = new ATMUI();
        myATM.run();
    }
}
