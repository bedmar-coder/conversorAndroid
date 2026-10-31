package dam.miguelangel.u2p4conversor;

/**
 * Thrown when the user enters a number smaller than 1.
 */
public class NumberTooSmallException extends RuntimeException {
    public NumberTooSmallException() {
        super("only numbers >= 1");
    }
}
