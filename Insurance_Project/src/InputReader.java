import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class opens the scanner and reads inputs from the user. It then directs to
 * other classes and methods based on given input.
 */
class InputReader {

    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;

    /**
     * Private constructor that opens a scanner.
     */
    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    /**
     * The main way to construct an InputReader class.
     * It is static and ensures that there is only one instance of InputReader at a time.
     */
    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    /**
     * This function determines which code to run based on user input.
     * It detects the first line of the block and calls a function based on that.
     * If there is an invalid command given, an error will be thrown with the line number.
     */
    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;
        try {
            while (keyboard.hasNext()) {
                lineNumber++;
                line = keyboard.nextLine();
                if (line.startsWith("PRINT ")) {
                    commands.add(makePrintCommand(line));
                } else if (line.startsWith("BEGIN_")) {
                    commands.add(makeBlockCommand(line));
                } else if (line.equals("FINISH")) {
                    break;
                } else if (!line.equals("")) {
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
            }
        } catch (BadCommandException e) {
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        return commands;
    }


    /**
     * Creates a new block command based on the input. Goes through the rest of the command
     * line by line and adds each line as a tag. Checks validity of a tag before adding.
     */
    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));

        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                String [] tokens = line.split(" ", 3);
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    /**
     * Returns a new print command based on user input. There must be exactly four tokens
     * supplied to be a valid print command.
     */
    private Command makePrintCommand(String line) {
        String[] tokens = line.split(" ", 5);
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
    }
}