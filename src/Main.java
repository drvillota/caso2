package src;
import java.util.*;

public class Main {
    
    // constants
	private static final int NUMERO_MARCOS = 8;
	private static final int NUMERO_PAGINAS = 8;
	private static final int NUMERO_REFERENCIAS = 32;

    public static void main(String [] args) {
		try {
            Scanner leer = new Scanner(System.in);
			
            int tamanoTLB;
            
			//Dialogo programa
			System.out.println("Indique el número de entradas de la TLB (8, 16, 32, 64)");
			tamanoTLB = leer.nextInt();
			System.out.println("El número de entradas de la TLB es: " + tamanoTLB);

			System.out.println("El número de marcos de página es: " + NUMERO_MARCOS);
			
			System.out.println("El número de páginas es: " + NUMERO_PAGINAS);
			
			//System.out.println("El número de referencias es: " + NUMERO_REFERENCIAS);
			
            MemoryManager mm = new MemoryManager(NUMERO_REFERENCIAS, tamanoTLB, NUMERO_MARCOS, NUMERO_PAGINAS);
            mm.start();
            
		} catch(Exception e) {
			
		}
	}
}
