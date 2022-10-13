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
			int numRef = leer.nextInt();
			System.out.println("El número de referencias es: " + numRef);
			
			int [] refId = new int[numRef];
			String [] refBit = new String[numRef];
			
			for(int i=0; i<numRef; i++) {
				String s[] = br.readLine().split(",");
				refId[i] = Integer.parseInt(s[0]);
				refBit[i] = s[1];
			}
			
		} catch(Exception e) {
			
		}
	}

}