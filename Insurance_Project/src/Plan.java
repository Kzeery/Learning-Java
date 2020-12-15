import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The Plan class defines plans and customer eligibilty for those plans. It is a parent
 * of the classes HomePlan and CarPlan.
 */
abstract class Plan {

    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();

    /**
     * Constructor for Plan. Initializes all the variables above with the tags supplied.
     * Variables like customer age and income may not exist in the tags, and such will
     * not be updated in the criteria.
     */
    Plan(HashMap<String, ArrayList<Tag>> tags) {
        name = tags.get("NAME").get(0).getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").get(0).getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").get(0).getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").get(0).getValue());


        if (tags.get("CUSTOMER.AGE") != null) {
            for (Tag tag: tags.get("CUSTOMER.AGE")) {
                customerAgeCriterion.addCriterion(tag);
            }
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            for (Tag tag: tags.get("CUSTOMER.INCOME")) {
                customerIncomeCriterion.addCriterion(tag);
            }
        }

    }

    /**
     * Abstract function to be implemented in subclasses.
     */
    abstract boolean isEligible(Insurable insurable, Date date);

    /**
     * Abstract function to be implemented in subclasses.
     */
    abstract Insurable getInsuredItem(Customer customer, Database database);


    /**
     * Checks to see if a customer is eligible for the plan based on the customer age
     * and their income.
     */
    boolean isEligible(Customer customer, Date currentDate) {
        // Extracting the approximate age of the customer (just based on the calendar years)
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        // Checking if the age is in the range.
        if (!customerAgeCriterion.isInRange(age))
            return false;
        // Checking if the income is in the range.
        return customerIncomeCriterion.isInRange(customer.getIncome());
    }

    /**
     * Getter for name of the plan.
     */
    String getName() {
        return name;
    }

    /**
     * Getter for premium of the plan.
     */
    long getPremium() {
        return premium;
    }

    /**
     * Getter for max coverage per claim of the plan.
     */
    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    /**
     * Getter for deductible of the plan.
     */
    long getDeductible() {
        return deductible;
    }
}
