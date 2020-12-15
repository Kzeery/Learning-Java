import java.text.ParseException;

/**
 * CommandHandler is a class that links and runs commands to a database.
 */
class CommandHandler {
    Database database;

    /**
     * Constructor for CommandHandler. Sets the database to be the database given.
     */
    CommandHandler(Database database) {
        this.database = database;
    }

    /**
     * Runs a command using the database.
     */
    void run(Command command) throws ParseException {
        command.run(database);
    }
}
