/**
 * Custom Exception that is usually thrown when a command is invalid.
 */
class BadCommandException extends RuntimeException {
    BadCommandException(String message) {
        super(message);
    }
}
