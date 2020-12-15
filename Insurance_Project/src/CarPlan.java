import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Subclass of Plan. This subclass handles plans that are specifically for cars.
 */
class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";
    RangeCriterion mileageCriterion = new RangeCriterion();

    /**
     * Constructor for CarPlan. Uses the constructor for Plan with the given Hashmap
     * input. Then it sets the mileageCriterion based on the tags if it exists.
     */
    CarPlan(HashMap<String, ArrayList<Tag>> tags) {
        super(tags);

        if (tags.get("CAR.MILEAGE") != null) {
            for (Tag tag : tags.get("CAR.MILEAGE")) {
                mileageCriterion.addCriterion(tag);
            }
        }
    }

    /**
     * Overriding the abstract isEligible method from the Plan class.
     * Checks if the input is an instance of car. If it is, it can cast the insurable object to
     * a car. Then it checks if the mileage of the car is within a valid range. If this is true,
     * the car is eligible for the plan.
     */
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Car))
            return false;
        Car car = (Car) insurable;
        return mileageCriterion.isInRange(car.getMileage());
    }

    /**
     * Overriding the abstract getInsuredItem method from the Plan class. It returns the
     * insurable car object from the database with the name of the customer.
     */
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
