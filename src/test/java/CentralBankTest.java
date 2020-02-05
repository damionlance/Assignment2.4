import org.junit.jupiter.api.Test;

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
}