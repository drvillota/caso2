package src;

import java.io.*;

/*
 * Clase encargada de cargar las referencias del proceso e ir actualizando el estado de la TLB, la RAM y la tabla de páginas 
 */
public class MemoryManager extends Thread{

    private int tamanoTLB;
    private int numeroDeReferencias;

    private int nextFrameNumber;	/* the next available frame number */
	private int nextTLBEntry;		/* the next available entry in the TLB */

    // constants
    private TP[] tablaPaginas;	/* Tabla de páginas */

    private Integer[] RAM;		/* Memoria RAM */
	
	private TLB[] tablaTLB;		/* TLB */

    private int[] referencias; /* Referencias del archivo */

    private int fallosPagina;			/* the number of page faults */
	private int aciertosTLB;			/* the number of TLB hits */

    private File archivo;
	private FileReader fr;
    private BufferedReader br;

    //Constructor de la tp y tlb
    public MemoryManager(int pNumeroReferencias, int pTamanoTLB, int pNumeroMarcos, int pNumeroPaginas) throws FileNotFoundException {
        numeroDeReferencias = pNumeroReferencias;

        /* Inicializa la TP */
		tablaPaginas = new TP[pNumeroPaginas];
		for (int i = 0; i < pNumeroPaginas; i++) {
			tablaPaginas[i] = new TP();
		}

		/* Inicializa TLB */
		tablaTLB = new TLB[pTamanoTLB];
		for (int i = 0; i < pTamanoTLB; i++){
			tablaTLB[i] = new TLB();
		}

		/* Inicializa la RAM */
		RAM = new Integer[pNumeroMarcos];
		for (int i = 0; i < pNumeroMarcos; i++) {
			RAM[i] = i;
		}

        nextFrameNumber = nextTLBEntry = 0;
		
		/* Inicializa el contador de fallos y aciertos */
		fallosPagina = 0;
		aciertosTLB = 0;
    }

    /*
     * Carga las referencias de los archivos
     */
    public void cargarReferencias() {
        try {
            archivo = new File("data/test_A_R32_P8.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            referencias = new int[numeroDeReferencias];
            String x;
            while((x=br.readLine()) != null) {
                for(int i=0; i<numeroDeReferencias; i++) {
                    referencias[i] = Integer.parseInt(x);
                    System.out.println(x);
                }
            }
            
            br.close();

            System.out.println("Se cargaron " + referencias.length + " referencias");

        } catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
    }    
    
    /*
     * Revisa si la página se encuentra en la TLB
     * @return Devuelve el número de marco si se encuentra, -1 de lo contrario
     */
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

    public synchronized void actualizarTLByTP() {
        int  numeroDePagina;
        int numeroDeMarco;

        try {

            for (int i = 0; i < referencias.length; i++) {
                numeroDePagina = referencias[i];

                System.out.println("Pagina referencia = " + numeroDePagina);

                //Revisa si la página está en la TLB
                if((numeroDeMarco = revisarTLB(numeroDePagina)) == -1) { /* No se encuentra en la TLB */

                    System.out.println("Numero de marco en TLB"+numeroDeMarco);
                    // Revisa la tabla de páginas
                    if (tablaPaginas[numeroDePagina].obtenerNumeroMarco() != -1) {
                        numeroDeMarco = tablaPaginas[numeroDePagina].obtenerNumeroMarco();

                        System.out.println("Si está en la TP");

                    } else { 	/** Fallo de página **/
                        fallosPagina++;
                        numeroDeMarco = numeroDePagina; /* Provicional */
                        tablaPaginas[numeroDePagina].actualizarTP(numeroDeMarco);
                        //Algorithm aging
                        System.out.println("FALLO DE PÁGINA");
                    }
                    // Actualiza TLB
                    tablaTLB[nextTLBEntry].registrarTLB(numeroDePagina, numeroDeMarco);
                    nextTLBEntry++;
                    
                    System.out.println("-----" + nextTLBEntry);
                }
            }    
        } catch (Exception e) {
        }
        
    }

    /** 
	 * Generate statistics.
	 */
	public void generateStatistics() {
		System.out.println("Fallos de página = " + fallosPagina);
        System.out.println("Aciertos en TLB = " + aciertosTLB);
	}

    public void run() {
		cargarReferencias();
        actualizarTLByTP();
        generateStatistics();
	}
}