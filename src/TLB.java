package src;

public class TLB {
    
    private int numMarco;      // the physical frame number
    private int numPagina;     // the virtual page number


    public TLB(){
        numMarco = -1;
        numPagina = -1;
    }

    public void registrarTLB(int pNumMarco, int pNumPagina){
        numMarco = pNumMarco;
        numPagina = pNumPagina;
    }

    public boolean revisarNumeroPagina(int pageNumber) {
		if (pageNumber == this.numPagina)
			return true;
		else
			return false;
	}

	public int obtenerNumeroMarco() {
		return numMarco;
	}
}
