package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Clase mainCliente
 * Fecha: 31/1/2019
 * Autores: Alejandra Gonz�lez - Angie Valentina C�rdoba
*/
public class mainCliente {
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * Se instancia un elemento de la misma clase
		*/
		TPCConnection conexion = TPCConnection.getInstance();
		/*
		 * Se selecciona la ip de usuario
		 */
		conexion.setIp("localhost");
		/*
		Se selecciona el puerto donde se recibir�n los datos
		*/
		conexion.setPuerto(5000);
		/*
		 * Se ejecuta el m�todo conector de la clase TCPConnection
		 */
		conexion.conector();
		
		/*
		 * Se lee cadena de texto enviada por el usuario
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String linea = in.readLine();
		
		while(!linea.equals("") && linea != null) {
			
			conexion.send(linea);
			
			linea = in.readLine();
		}
		
		
		
	}
	
}
