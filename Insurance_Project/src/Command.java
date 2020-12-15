import java.text.ParseException;

/**
 * abstract class with one abstract method "run". This ensures that all
 * subclasses of "Contain" have a "run" method that will be implemented.
 */
abstract class Command {
    abstract void run(Database database) throws ParseException;
}
