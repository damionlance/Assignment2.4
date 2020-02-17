import java.io.FileNotFoundException;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String acctId, double startingBalance, String password);

    public void closeAccount(String acctId) throws FileNotFoundException;
}
