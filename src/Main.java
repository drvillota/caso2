package src;
import java.io.*;
import java.util.Scanner;

public class Main {

	// constants
	private static final int NUMERO_MARCOS = 8;
	private static final int NUMERO_PAGINAS = 64;
	private static final int NUMERO_REFERENCIAS = 1024;

	private static int tamanoTLB;

	private int nextFrameNumber;	/* the next available frame number */
	private int nextTLBEntry;		/* the next available entry in the TLB */

	//private TP[] tablaPaginas;	/* the page table */
	private Integer[] tablaPaginas;	/* the page table */
	
	private Integer[] RAM;		/* physical memory (organized in frames) */
	
	private TLB[] tablaTLB;		/* the TLB */

	private int fallosPagina;			/* the number of page faults */
	private int aciertosTLB;			/* the number of TLB hits */

	public Main() {
		/* Inicializa la TP */
		tablaPaginas = new Integer[NUMERO_REFERENCIAS];
		for (int i = 0; i < NUMERO_REFERENCIAS; i++) {
			//tablaPaginas[i] = new TP();
			tablaPaginas[i] = -1;
		}

		/* Inicializa TLB */
		//tablaTLB = new TLB[tamanoTLB];
		tablaTLB = new TLB[tamanoTLB];
		for (int i = 0; i < tamanoTLB; i++){
			tablaTLB[i] = new TLB();
		}

		/* Inicializa la RAM */
		RAM = new Integer[NUMERO_MARCOS];
		for (int i = 0; i < NUMERO_MARCOS; i++) {
			RAM[i] = i;
		}

		nextFrameNumber = nextTLBEntry = 0;
		
		/* Inicializa el contador de fallos y aciertos */
		fallosPagina = 0;
		aciertosTLB = 0;
	}

	public int revisarTLB(int pNumeroPagina) {
		int numeroMarco = -1;

		for (int i = 0; i < tamanoTLB; i++) {
			if (tablaTLB[i].revisarNumeroPagina(pNumeroPagina)) {
				numeroMarco = tablaTLB[i].obtenerNumeroMarco();
				aciertosTLB++;
				break;
			}
		}
		return numeroMarco;
	}

    public static void main(String [] args) {

		// vars
		//int numeroDeMarcos = 8;
		//int numeroDePaginas = 64;
		
		try {
			File archivo = new File("data/");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);

            Scanner leer = new Scanner(System.in);
			
			//Dialogo programa
			System.out.println("Indique el número de entradas de la TLB (8, 16, 32, 64)");
			tamanoTLB = leer.nextInt();
			System.out.println("El número de entradas de la TLB es: " + tamanoTLB);

			System.out.println("El número de marcos de página es: " + NUMERO_MARCOS);
			
			System.out.println("El número de páginas es: " + NUMERO_PAGINAS);
			
			int numRefs = leer.nextInt();
			System.out.println("El número de referencias es: " + numRefs);
			
			//Carga las referencias
			int [] refsId = new int[numRefs];
			String stringRef;
			while((stringRef = br.readLine()) != null) {
				for(int i=0; i<numRefs; i++) {
					refsId[i] = Integer.parseInt(stringRef);
				}
			}
			br.close();
			
		} catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}

}