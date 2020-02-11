import org.junit.jupiter.api.Test;

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
    void createAccountTest() {
        CentralBank newBank = new CentralBank();
        newBank.createAccount("12345", 500);
        BankAccount account = json.readAccountFromJSON("12345");
        assertEquals(account.getBalance(), 500);
    }
}