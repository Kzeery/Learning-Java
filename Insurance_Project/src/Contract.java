import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Defines the Contract class where each contract has a name, customer name, plan name,
 * start and end dates, and discount percentage.
 */
class Contract {
    private String contractName;
    private String customerName;
    private String planName;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;

    static final String inputTag = "CONTRACT";

    /**
     * Constructor for Contract. Sets all the variables listed above based on the supplied values.
     */
    Contract(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        customerName = tags.get("CUSTOMER_NAME").get(0).getValue();
        planName = tags.get("PLAN_NAME").get(0).getValue();
        startDate = Utils.convertDate(tags.get("START_DATE").get(0).getValue());
        endDate = Utils.convertDate(tags.get("END_DATE").get(0).getValue());
        discountPercentage = Integer.parseInt(tags.get("DISCOUNT_PERCENTAGE").get(0).getValue());
    }

    /**
     * Getter for customerName.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Getter for planName.
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Getter for startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Getter for endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Getter for discountPercentage.
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Getter for inputTag.
     */
    public static String getInputTag() {
        return inputTag;
    }

    /**
     * Getter for contractName.
     */
    public String getContractName() {
        return contractName;
    }
}
