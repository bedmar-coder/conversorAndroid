package dam.miguelangel.u2p4conversor;

public class NumeroMenorException extends RuntimeException {
    public NumeroMenorException() {
        super("solo números >=1");
    }
}
