import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Subclass of Plan. This subclass handles plans that are specifically for homes.
 */
class HomePlan extends Plan {
    static final String inputTag = "HOME_PLAN";
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    /**
     * Constructor for HomePlan. Uses the constructor for Plan with the given Hashmap
     * input. Then it sets the homeValueCriterion and homeAgeCriterion based on the given
     * tags.
     */
    HomePlan(HashMap<String, ArrayList<Tag>> tags) {
        super(tags);

        if (tags.get("HOME.VALUE") != null) {
            for (Tag tag : tags.get("HOME.VALUE")) {
                homeValueCriterion.addCriterion(tag);
            }
        }
        if (tags.get("HOME.AGE") != null) {
            for (Tag tag : tags.get("HOME.AGE")) {
                homeAgeCriterion.addCriterion(tag);
            }
        }
    }

    /**
     * Overriding the abstract isEligible method from the Plan class.
     * Checks if the input is an instance of Home. If it is, it can cast the insurable object to
     * a home. Then it checks if the value and age of the car are within the criteria. Returns
     * true if all checks return true.
     */
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Home))
            return false;
        Home home = (Home) insurable;
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the approximate age of the home (calendar years)
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    /**
     * Overriding the abstract getInsuredItem method from the Plan class. It returns the
     * insurable home object from the database with the name of the customer.
     */
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
