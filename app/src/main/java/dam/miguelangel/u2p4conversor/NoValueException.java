package dam.miguelangel.u2p4conversor;

/**
 * Thrown when the user presses "Convert" with an empty field.
 */
public class NoValueException extends RuntimeException {
    public NoValueException() {
        super("The value can't be empty");
    }
}
