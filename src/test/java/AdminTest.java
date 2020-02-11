import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AdminTest {
    private List<String> validAcc=Arrays.asList("ValidCustomer1",
            "ValidCustomer2", "ValidCustomer3");
    @Test
    void freezeBankAccountTest(){
        BankAccount bankAccount1=JsonForTests.readAccountFromJSON(this.validAcc.get(0));
        Admin admin1= new Admin(bankAccount1.getEmail(),bankAccount1.getPassword(),bankAccount1.getAcctId());
        admin1.freezeAccount(bankAccount1.getAcctId());
        b

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
