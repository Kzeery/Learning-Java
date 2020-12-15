import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Defines the Customer class where each customer has a name, DOB, and income.
 */
class Customer {
    private String name;
    private Date dateOfBirth;
    private long income;

    static final String inputTag = "CUSTOMER";

    /**
     * Constructor for Customer class. Sets the name, date of birth, and income
     * based on supplied hashmap of tags.
     * */
    Customer(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        name = tags.get("NAME").get(0).getValue();
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").get(0).getValue());
        income = Long.parseLong(tags.get("INCOME").get(0).getValue());
    }

    /**
     * Basic getter for name.
     */
    public String getName() {
        return name;
    }

    /**
     * Basic getter for date of birth.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Basic getter for income.
     */
    public long getIncome() {
        return income;
    }

    /**
     * Basic getter for input tag.
     */
    public static String getInputTag() {
        return inputTag;
    }
}
