import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void isAmountValidTest() {

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
    void checkBalanceTest(){
        BankAccount bankAccount = new BankAccount(100, "abcd", "1234", "");
        BankAccount bankAccount1 = new BankAccount(1, "efgh", "5678", "");
        BankAccount bankAccount2 = new BankAccount(1.00, "ijkl", "9012", "");
        BankAccount bankAccount3 = new BankAccount(1.000, "mnop", "3456", "");
        BankAccount bankAccount4 = new BankAccount(1.0, "qrst", "7890", "");
        BankAccount bankAccount5 = new BankAccount(0, "uvwx", "12345", "");
        BankAccount bankAccount6 = new BankAccount(0.01, "yzabc", "67890", "");

        assertEquals(100, bankAccount.getBalance());
        assertEquals(1, bankAccount1.getBalance()); //border
        assertEquals(1.00, bankAccount2.getBalance());
        assertEquals(1.00, bankAccount3.getBalance());
        assertEquals(1.0, bankAccount4.getBalance());
        assertEquals(0, bankAccount5.getBalance()); //border
        assertEquals(0.01, bankAccount6.getBalance()); //border

    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount(200, "abcde", "1234", "");
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());


        // Equivalence Class: amount > balance
        // Border Case: No
        //overdraw positive account test
        BankAccount bankAccount1 = new BankAccount(10, "abcd", "1234", "");
        assertThrows(RuntimeException.class, ()-> bankAccount1.withdraw(40));

        //overdraw negative account test
        BankAccount bankAccount2 = new BankAccount(-10, "efgh", "5678", "");
        // Equivalence Class: amount > balance
        // Border Case: No
        assertThrows(RuntimeException.class, ()-> bankAccount2.withdraw(200));

        // Equivalence Class: amount < balance, amount positive
        // Border Case: No
        //successful withdraw test
        BankAccount bankAccount3 = new BankAccount(100, "asdfghjkjhgfd", "123456", "");
        bankAccount3.withdraw(20);

        assertEquals(80, bankAccount3.getBalance());

        //negative tests
        BankAccount bankAccount4 = new BankAccount(10, "abcmailcom", "456789", "");
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-0.01));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-0.00));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-0.10));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-0.0001));

        //positive tests
        BankAccount bankAccount6 = new BankAccount(1000, "jhtyjhfgtyh", "544767875", "");
        bankAccount6.withdraw(0.01);
        assertEquals(999.99, bankAccount6.getBalance());

        bankAccount6.withdraw(0.10);
        assertEquals(999.89, bankAccount6.getBalance());

        bankAccount6.withdraw(10.00);
        assertEquals(989.89, bankAccount6.getBalance());


        //valid decimal points
        BankAccount bankAccount5 = new BankAccount(1000, "jaguar", "hammerhead", "");
        bankAccount5.withdraw(0.01);
        assertEquals(999.99, bankAccount5.getBalance());

        bankAccount5.withdraw(0.1);
        assertEquals(999.89, bankAccount5.getBalance());

        bankAccount5.withdraw(10.00);
        assertEquals(989.89, bankAccount5.getBalance());

        bankAccount5.withdraw(10.000);
        assertEquals(979.89, bankAccount5.getBalance());

        bankAccount5.withdraw(10);
        assertEquals(969.89, bankAccount5.getBalance());

        //non-valid decimal points
        BankAccount bankAccount7 = new BankAccount(10, "cheetah", "rhino", "");
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.withdraw(0.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.withdraw(0.0001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.withdraw(0.00001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.withdraw(10.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.withdraw(0.0099));

    }

    @Test
    void depositTest(){
        BankAccount BankAccount = new BankAccount(200, "elephant", "zebra", "");

        //positive amounts
        BankAccount.deposit(0.01); //border
        assertEquals(200.01,BankAccount.getBalance());

        BankAccount.deposit(0.10);
        assertEquals(200.11, BankAccount.getBalance());

        BankAccount.deposit(10.00);
        assertEquals(210.11, BankAccount.getBalance());

        BankAccount.deposit(10);
        assertEquals(220.11, BankAccount.getBalance());


        //negative and zero amounts

        BankAccount BankAccount2 = new BankAccount(100, "toucan", "sloth", "");
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(0.00)); //border
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(-0.01)); //border
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(-0.10));
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(-1.00));
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(-1));
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(-10));
        assertThrows(IllegalArgumentException.class, ()-> BankAccount2.deposit(-100));

        //valid decimal point

        BankAccount BankAccount3 = new BankAccount(100, "meerkat", "lion", "");

        BankAccount3.deposit(0.01);
        assertEquals(100.01, BankAccount3.getBalance());

        BankAccount3.deposit(0.1);
        assertEquals(100.11, BankAccount3.getBalance());

        BankAccount3.deposit(10);
        assertEquals(110.11, BankAccount3.getBalance());

        BankAccount3.deposit(0.100);
        assertEquals(110.21, BankAccount3.getBalance());

        BankAccount3.deposit(0.10000);
        assertEquals(110.31, BankAccount3.getBalance());

        BankAccount3.deposit(0.0100000000);
        assertEquals(110.32, BankAccount3.getBalance());

        BankAccount3.deposit(1);
        assertEquals(111.11, BankAccount3.getBalance());


        //non-valid decimal point

        BankAccount BankAccount4 = new BankAccount(100, "jackrabbit", "chinchilla", "");

        assertThrows(IllegalArgumentException.class, ()-> BankAccount4.deposit(0.000)); //border
        assertThrows(IllegalArgumentException.class, ()-> BankAccount4.deposit(0.001)); //border
        assertThrows(IllegalArgumentException.class, ()-> BankAccount4.deposit(0.0001));
        assertThrows(IllegalArgumentException.class, ()-> BankAccount4.deposit(0.00001));
        assertThrows(IllegalArgumentException.class, ()-> BankAccount4.deposit(0.1234));


=======
    void JSONReadWrite() {
        BankAccount testAccount = new BankAccount("a.b@c.com", 500, "123", "myPassword");

        json.writeAccountToJSON(testAccount);
        BankAccount returnedAccount = json.readAccountFromJSON("123");
        assertEquals(testAccount.getBalance(), returnedAccount.getBalance());
>>>>>>> 8fae3c11e7042c21b879993386c10383a839ca36

    }

    @Test
<<<<<<< HEAD
    void tranferTest(){

        BankAccount BankAccount1 = new BankAccount("12345", 100, "");
        BankAccount bankAccount2 = new BankAccount("67890", 100, "");

        BankAccount BankAccount3 = new BankAccount("abcde", 100, "");
        BankAccount BankAccount4 = new BankAccount("fghi", 100, "");

        BankAccount BankAccount5 = new BankAccount("kelsey", 100000, "");
        BankAccount BankAccount6 = new BankAccount("kerry", 10, "");

    }

    @Test
    void transactionHistoryTest(){

    }

=======
    @Test
    void createAccountTest() {
        CentralBank newBank = new CentralBank();
        newBank.createAccount("12345", 500);
        BankAccount account = json.readAccountFromJSON("12345");
        assertEquals(account.getBalance(), 500);
      
    @Test
    void closeAccount() {
        BankAccount testAccount = new BankAccount("a.b@c.com", 500, "123", "myPassword");
        CentralBank mybank = new CentralBank();

        json.writeAccountToJSON(testAccount);
        mybank.closeAccount(testAccount.getAcctId());
        //assertThrows(FileNotFoundException.class, ()->json.readAccountFromJSON(testAccount.getAcctId()));
    }
>>>>>>> 8fae3c11e7042c21b879993386c10383a839ca36
}