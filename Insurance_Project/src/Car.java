import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Defines the Car class which is a subclass of the Insurable class. It has all the attributes
 * of the Insurable class, as well as make, model, purchase date, and mileage.
 */
class Car extends Insurable {

    private String make;
    private String model;
    private Date purchaseDate;
    private long mileage;

    static final String inputTag = "CAR";

    /**
     * Constructor for car. Uses the constructor for Insurable first.
     * Adds the make, model, purchase date, and mileage based on tags.
     */
    Car(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        super(tags);
        make = tags.get("MAKE").get(0).getValue();
        model = tags.get("MODEL").get(0).getValue();
        purchaseDate = Utils.convertDate(tags.get("PURCHASE_DATE").get(0).getValue());
        mileage = Long.parseLong(tags.get("MILEAGE").get(0).getValue());
    }

    /**
     * Getter for owner name.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Getter for make.
     */
    public String getMake() {
        return make;
    }

    /**
     * Getter for owner model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Getter for purchase date.
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Getter for mileage.
     */
    public long getMileage() {
        return mileage;
    }

    /**
     * Getter for input tag.
     */
    public static String getInputTag() {
        return inputTag;
    }
}

