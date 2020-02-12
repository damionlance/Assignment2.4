import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

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
    void JSONReadWrite() throws IOException, org.json.simple.parser.ParseException {
        BankAccount testAccount = new BankAccount(500, "123", "myPassword", "");

        //valid account Id
        json.writeAccountToJSON(testAccount);
        BankAccount returnedAccount = json.readAccountFromJSON("123");
        assertEquals(testAccount.getBalance(), returnedAccount.getBalance());
        assertEquals(testAccount.getPassword(), returnedAccount.getPassword());

        //invalid account Id
        assertThrows(FileNotFoundException.class, ()->json.readAccountFromJSON("99999999"));
    }

    @Test
    void createAccountTest() throws IOException, org.json.simple.parser.ParseException {
        CentralBank bank = new CentralBank();

        //valid account - valid starting balance (only way for account to be valid right now)
        bank.createAccount("12345", 500, "password");
        BankAccount account = json.readAccountFromJSON("12345");
        assertEquals(500, account.getBalance());

        //invalid account - invalid starting balance
        assertThrows(IllegalArgumentException.class, ()->bank.createAccount("12345", 0, "pass"));

    }

    @Test
    void closeAccount() throws FileNotFoundException {
        CentralBank bank = new CentralBank();

        //valid account
        bank.createAccount("12345", 500, "password");
        bank.closeAccount("12345");

        //invalid account
        assertThrows(FileNotFoundException.class, ()->bank.closeAccount("12345"));

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
        //valid test - account 111 is in the bank
        assertTrue(is111inAccount);
        //invalid test - account 999 is not in the bank
        assertFalse(is999inAccount);
    }
}