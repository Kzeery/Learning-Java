/**
 * PrintCommand class defines methods to print plans and customers based on given input.
 */
class PrintCommand extends Command {
    private String entityType;
    private String queryType;
    private String queryValue;

    /**
     * Constructor for PrintCommand. Uses the constructor for Command and then sets entityType,
     * queryType, and queryValue based on given tokens.*/
    PrintCommand(String[] tokens) {
        super();
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
    }

    /**
     * This is the method to try to run the command. You can only print a customer or a plan.
     * Anything else will throw an exception.
     */
    @Override
    void run(Database database) {
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    /**
     * The method of printing a customer's total received or total claimed values.
     * It uses the database methods to find the values based on the customer name.
     */
    private void runPrintCustomer(Database database) {
        if (queryType.equals("TOTAL_CLAIMED")) {
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        } else if (queryType.equals("TOTAL_RECEIVED")) {
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                            " is " + database.totalReceivedAmountByCustomer(queryValue));
        } else {
            throw new BadCommandException("Invalid PRINT CUSTOMER command.");
        }
    }

    /**
     * The method of printing the number of customers under a plan or the amount payed to
     * customers under a given plan. It uses database methods to find the values based
     * on the given plan name.
     */
    private void runPrintPlan(Database database) {
        if (queryType.equals("NUM_CUSTOMERS")) {
            System.out.println("Number of customers under " + queryValue +
                    " is " + database.customersUnderPlan(queryValue));
        } else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")) {
            System.out.println("Total amount payed under " + queryValue +
                    " is " + database.totalReceivedAmountByPlan(queryValue));
        } else {
            throw new BadCommandException("Invalid PRINT PLAN command.");
        }
    }
}
