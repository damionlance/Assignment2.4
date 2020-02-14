import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AdminTest {
    private List<String> validAcc=Arrays.asList("ValidCustomer1",
            "ValidCustomer2", "ValidCustomer3");
    @Test
    void freezeBankAccountTest() throws IllegalArgumentException{
        Admin admin1= new Admin("tempAdminPassword");//make a list of accepted admin passwords
        List<BankAccount> accounts=Arrays.asList(JsonForTests.readAccountFromJSON(validAcc.get(0)),
                JsonForTests.readAccountFromJSON(validAcc.get(1)),JsonForTests.readAccountFromJSON(validAcc.get(2)));
        admin1.setAccounts(accounts);
        BankAccount bankAccount1=accounts.get(0);

        admin1.freezeAccount(bankAccount1.getAcctId());

        assertThrows(IllegalArgumentException.class,()->bankAccount1.withdraw(1));
        assertThrows(IllegalArgumentException.class,()->bankAccount1.deposit(30));

    }

    @Test
    void unfreezeBankAccountTest()throws InsufficientFundsException{
        Admin admin1= new Admin("tempAdminPassword");//make a list of accepted admin passwords
        List<BankAccount> accounts=Arrays.asList(JsonForTests.readAccountFromJSON(validAcc.get(0)),
                JsonForTests.readAccountFromJSON(validAcc.get(1)),JsonForTests.readAccountFromJSON(validAcc.get(2)));

        admin1.setAccounts(accounts);
        BankAccount bankAccount1=accounts.get(0);
        admin1.freezeAccount(bankAccount1.getAcctId());

        assertThrows(IllegalArgumentException.class,()->bankAccount1.withdraw(1));
        assertThrows(IllegalArgumentException.class,()->bankAccount1.deposit(30));
        admin1.unfreezeAcct(bankAccount1.getAcctId());

        bankAccount1.deposit(20);
        assertEquals(520.0,bankAccount1.getBalance());
        bankAccount1.withdraw(20);
        assertEquals(500,bankAccount1.getBalance());



    }
    @Test

    void findAcctIdsWithSuspiciousActivity(){

    }
    @Test
    void calcTotalBankAssets(){
        Admin admin1= new Admin("tempAdminPassword");//make a list of accepted admin passwords
        List<BankAccount> accounts=Arrays.asList(JsonForTests.readAccountFromJSON(validAcc.get(0)),
                JsonForTests.readAccountFromJSON(validAcc.get(1)),JsonForTests.readAccountFromJSON(validAcc.get(2)));
        admin1.setAccounts(accounts);
        assertEquals(1350.0,admin1.getAssetTotal());
    }
//TODO:DELETE JSON FILE IN CENTRAL BANK TEST FILE

}
