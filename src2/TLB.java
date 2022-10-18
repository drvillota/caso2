package src2;

public class TLB {
    
    private int numMarco;      // the physical frame number
    private int numPagina;     // the virtual page number
    
    //private int numRefs;

    public TLB(){
        numMarco = -1;
        numPagina = -1;
        //numRefs = pNumRefs;
    }

    public void registrarTLB(int pNumMarco, int pNumPagina/*, int pNumRefs*/){
        numMarco = pNumMarco;
        numPagina = pNumPagina;
        //numRefs = pNumRefs;
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
