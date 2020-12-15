import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Insurable class defines objects that are insurable such as homes and cars.
 */
class Insurable {
    protected String ownerName;
    protected long value;

    /**
     * Constructor for Insurable class. Takes a hashmap of tags and
     * sets the value and owner name based on them.
     */
    Insurable(HashMap<String, ArrayList<Tag>> tags) {
        ownerName = tags.get("OWNER_NAME").get(0).getValue();
        value = Long.parseLong(tags.get("VALUE").get(0).getValue());
    }

    /**
     * Getter for owner name.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Getter for value.
     */
    public long getValue() {
        return value;
    }
}
