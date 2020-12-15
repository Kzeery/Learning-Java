import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class which runs code. Handles possible errors.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Opens a reader, gets the commands, iterates through them, and then runs them
            InputReader inputReader = InputReader.getInstance();
            ArrayList<Command> commands = inputReader.getCommands();
            Iterator<Command> currentCommand = commands.iterator();

            CommandHandler commandHandler = new CommandHandler(new Database());

            while (currentCommand.hasNext()) {
                commandHandler.run(currentCommand.next());
            }
        } catch (ParseException e) {
            // Will usually occur if an inputted date format is invalid. Prints error message.
            System.out.println(e.getMessage());
        } catch (BadCommandException e) {
            // Will occur if a command input is invalid. Prints error message.
            System.out.println(e.getMessage());
        }
    }
}
