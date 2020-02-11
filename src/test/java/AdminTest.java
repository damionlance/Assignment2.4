import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AdminTest {
    private List<String> validAcc=Arrays.asList("ValidCustomer1",
            "ValidCustomer2", "ValidCustomer3");
    @Test
    void freezeBankAccountTest() throws IllegalArgumentException{
        BankAccount bankAccount1=JsonForTests.readAccountFromJSON(this.validAcc.get(0));
        Admin admin1= new Admin("tempAdminPassword");//make a list of accepted admin passwords
     //   admin1.freezeAccount(bankAccount1.getAcctId());



    }

    @Test
    void unfreezeBankAccountTest(){

    }
    @Test

    void findAcctIdsWithSuspiciousActivity(){

    }
    @Test
    void calcTotalAssets(){

    }
//TODO:DELETE JSON FILE IN CENTRAL BANK TEST FILE

}
