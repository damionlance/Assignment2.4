import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class json {

    public static void writeAccountToJSON(BankAccount accountToWrite){
        JSONObject obj = new JSONObject();
        obj.put("acctId", accountToWrite.getAcctId());
        obj.put("email", accountToWrite.getEmail());
        obj.put("password", accountToWrite.getPassword());
        obj.put("balance", accountToWrite.getBalance());

        try (FileWriter file = new FileWriter("src/main/resources/" + accountToWrite.getAcctId() + ".json")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BankAccount readAccountFromJSON(String acctId){
        JSONParser parser = new JSONParser();
        try(Reader reader = new FileReader("src/main/resources/" + acctId + ".json")){

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String email = (String) jsonObject.get("email");
            String password = (String) jsonObject.get("password");
            String acctIdRead = (String) jsonObject.get("acctId");
            double balance = (double) jsonObject.get("balance");

            BankAccount returnAccount = new BankAccount(email, balance, acctIdRead, password);
            return returnAccount;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    return null;
    }

}
