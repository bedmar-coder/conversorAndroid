package dam.miguelangel.u2p4conversor;

public class NingunValorException extends RuntimeException {
    public NingunValorException() {
        super("El valor no puede estar vacio");
    }
}
