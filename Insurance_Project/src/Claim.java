import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Defines the Claim class in which each claim has a contract name, an amount, a date, and a
 * successful boolean attribute.
 */
class Claim {
    private String contractName;
    private long amount;
    private Date date;
    private boolean successful;

    static final String inputTag = "CLAIM";

    /**
     * Constructor for Claim class. Sets contractName, date, and amount based on supplied
     * arguments.
     */
    Claim(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        date = Utils.convertDate(tags.get("DATE").get(0).getValue());
        amount = Long.parseLong(tags.get("AMOUNT").get(0).getValue());
    }

    /**
     * Getter for contractName.
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * Getter for amount.
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Getter for date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter for successful.
     */
    public boolean wasSuccessful() {
        return successful;
    }

    /**
     * Setter for successful.
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
