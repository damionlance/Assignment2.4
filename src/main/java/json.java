import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class json {

    public static void writeAccountToJSON(BankAccount accountToWrite){
        JSONObject obj = new JSONObject();
        obj.put("acctId", accountToWrite.getAcctId());
        obj.put("password", accountToWrite.getPassword());
        obj.put("balance", accountToWrite.getBalance());
        obj.put("transactionHist", accountToWrite.getTransactionHistory());

        try (FileWriter file = new FileWriter("src/main/resources/" + accountToWrite.getAcctId() + ".json")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BankAccount readAccountFromJSON(String acctId) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("src/main/resources/" + acctId + ".json");

        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        String password = (String) jsonObject.get("password");
        String acctIdRead = (String) jsonObject.get("acctId");
        String transactionHist = (String) jsonObject.get("transactionHist");
        double balance = (double) jsonObject.get("balance");

        BankAccount returnAccount = new BankAccount(balance, acctIdRead, password, transactionHist);

        return returnAccount;
    }

}
