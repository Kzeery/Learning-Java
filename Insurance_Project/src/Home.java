import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Defines the Home class which is a subclass of Insurable. It has all the attributes of
 * Insurable as well as postal code and build date.
 */
class Home extends Insurable {
    private String postalCode;
    private Date buildDate;

    static final String inputTag = "HOME";

    /**
     * Constructor for Home. Uses the Insurable constructor with the given arguments
     * and also sets postalCode and buildDate based on the arguments.
     */
    Home(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        super(tags);
        postalCode = tags.get("POSTAL_CODE").get(0).getValue();
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").get(0).getValue());
    }

    /**
     * Getter for postalCode.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Getter for buildDate.
     */
    public Date getBuildDate() {
        return buildDate;
    }

    /**
     * Getter for inputTag.
     */
    public static String getInputTag() {
        return inputTag;
    }
}
