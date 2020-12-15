import java.util.ArrayList;

/**
 * The Database class defines a database where customers, homes, cars, plans, contracts,
 * and claims are stored.
 */
class Database {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    /**
     * Method for adding a Home to the database
     */
    void insertHome(Home home) {
        homes.add(home);
    }

    /**
     * Method for adding a Car to the database
     */
    void insertCar(Car car) {
        cars.add(car);
    }

    /**
     * Method for adding a Customer to the database
     */
    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Method for adding a Plan to the database
     */
    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    /**
     * Method for adding a Claim to the database
     */
    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    /**
     * Method for adding a Contract to the database
     */
    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    /**
     * Getter for Plan. Returns null if the Plan does not exist.
     */
    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    /**
     * Getter for Customer. Returns null if the Customer does not exist.
     */
    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    /**
     * Getter for Contract. Returns null if the Contract does not exist.
     */
    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     */
    Home getHome(String ownnerName) {
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownnerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    Car getCar(String ownnerName) {
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownnerName))
                return car;
        }
        return null;
    }

    /**
     * Returns the amount of customers under a given plan.
     */
    int customersUnderPlan(String planName) {
        int total = 0;
        for (Contract contract : contracts) {
            if (contract.getPlanName().equals(planName))  {
                total++;
            }
        }
        return total;
    }

    /**
     * Goes through each claim a customer has made and adds up the total.
     * It then returns the total amount the customer has claimed.
     */
    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

    /**
     * For each of a customers claims it checks if the claim was successful. If it was,
     * it adds the amount that they received which is the deductible subtracted from
     * the claim amount. They cannot have received less than 0. It sums the total and
     * returns the total value.
     */
    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            if (contract.getCustomerName().equals(customerName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }

    /**
     * For each of a plan's claims it checks if the claim was successful. If it was,
     * it adds the amount that they received which is the deductible subtracted from
     * the claim amount. They cannot have received less than 0. It sums the total and
     * returns the total value.
     */
    long totalReceivedAmountByPlan(String planName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            if (contract.getPlanName().equals(planName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }
}
