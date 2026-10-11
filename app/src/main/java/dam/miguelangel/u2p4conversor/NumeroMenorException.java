package dam.miguelangel.u2p4conversor;

public class NumeroMenorException extends RuntimeException {
    public NumeroMenorException() {
        super("Sólo números >=1");
    }
    //creaeda excepción para el mensaje personalizado
}
