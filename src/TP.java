package src;

/*
 * Clase que representa una entrada en la tabla de páginas
 */
public class TP {
    private int numeroDeMarco;

    public TP() {
        numeroDeMarco = -1;
    }

    public int obtenerNumeroMarco() {
		return numeroDeMarco;
	}

	public void actualizarTP(int pNumeroDeMarco) {
		this.numeroDeMarco = pNumeroDeMarco;
	}
}
