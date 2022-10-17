package src;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
		try {
			File archivo = new File("data/");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);

            Scanner leer = new Scanner(System.in);
			
			int numMarcos = leer.nextInt();
			System.out.println("El número de marcos de página es: " + numMarcos);
			int numPaginas = leer.nextInt();
			System.out.println("El número de páginas es: " + numPaginas);
			int numRefs = leer.nextInt();
			System.out.println("El número de referencias es: " + numRefs);
			
			int [] refId = new int[numRefs];

			for(int i=0; i<numRefs; i++) {
				refId[i] = Integer.parseInt(br.readLine());
			}
			
		} catch(Exception e) {
			
		}
	}

}