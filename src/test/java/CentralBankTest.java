import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void isAmountValid() {

    }

    @Test
    void JSONReadWrite() {
        BankAccount testAccount = new BankAccount("a.b@c.com", 500, "123", "myPassword");

        json.writeAccountToJSON(testAccount);
        BankAccount returnedAccount = json.readAccountFromJSON("123");
        assertEquals(testAccount.getBalance(), returnedAccount.getBalance());

    }

    @Test
    void closeAccount() {
        BankAccount testAccount = new BankAccount("a.b@c.com", 500, "123", "myPassword");
        CentralBank mybank = new CentralBank();

        json.writeAccountToJSON(testAccount);
        mybank.closeAccount(testAccount.getAcctId());
        //assertThrows(FileNotFoundException.class, ()->json.readAccountFromJSON(testAccount.getAcctId()));
    }
}