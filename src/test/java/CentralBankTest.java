import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void isAmountValid() {

        //equivalence class: negative
        assertFalse(BankAccount.isAmountValid(-0.01)); //border
        assertFalse(BankAccount.isAmountValid(-10.00));
        assertFalse(BankAccount.isAmountValid(-0.10));

        //equivalence class: positive
        assertTrue(BankAccount.isAmountValid(0.01)); //border
        assertFalse(BankAccount.isAmountValid(0.00)); //border
        assertTrue(BankAccount.isAmountValid(0.10));
        assertTrue(BankAccount.isAmountValid(10));

        //equivalence class: valid decimal points
        assertTrue(BankAccount.isAmountValid(10.00)); //border
        assertTrue(BankAccount.isAmountValid(10.000)); //border
        assertTrue(BankAccount.isAmountValid(10.1));
        assertTrue(BankAccount.isAmountValid(10)); //border

        //equivalence class: non-valid decimal points
        assertFalse(BankAccount.isAmountValid(10.001)); //border
        assertFalse(BankAccount.isAmountValid(10.0001));
        assertFalse(BankAccount.isAmountValid(0.001));
        assertFalse(BankAccount.isAmountValid(0.0000001));

    }

    @Test
    void JSONReadWrite() {
        BankAccount testAccount = new BankAccount(500, "123", "myPassword", "");

        json.writeAccountToJSON(testAccount);
        BankAccount returnedAccount = json.readAccountFromJSON("123");
        assertEquals(testAccount.getBalance(), returnedAccount.getBalance());

    }

    @Test
    void createAccountTest() {
        CentralBank bank = new CentralBank();
        bank.createAccount("12345", 500, "password");
        BankAccount account = json.readAccountFromJSON("12345");
        assertEquals(500, account.getBalance());
    }
      
    @Test
    void closeAccount() {
        BankAccount testAccount = new BankAccount(500, "123", "myPassword", "");
        CentralBank mybank = new CentralBank();

        json.writeAccountToJSON(testAccount);
        mybank.closeAccount(testAccount.getAcctId());
        //assertThrows(FileNotFoundException.class, ()->json.readAccountFromJSON(testAccount.getAcctId()));
    }

    @Test
    void getAccountsTest() {
        CentralBank myBank = new CentralBank();

        myBank.createAccount("111", 500, "pass");

        String[] accounts = myBank.getAccountIDs();
        boolean is111inAccount = false;
        boolean is999inAccount = false;

        for(String id : accounts){
            if(id.equals("111")){
                is111inAccount = true;
            }
            if(id.equals("999")){
                is999inAccount = true;
            }
        }
        assertTrue(is111inAccount);
        assertFalse(is999inAccount);
    }
}