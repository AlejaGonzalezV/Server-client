package control;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Clase TPCConnection
 * Fecha: 31/1/2019
 * Autores: Alejandra Gonz�lez - Angie Valentina C�rdoba
*/
public class TPCConnection {

	/*
	 * Atributo que representa una instancia de la clase TPCConnection
	 */
	private static TPCConnection instance;
	/*
	 * Atributo que representa el puerto a utilizar
	 */
	private int puerto = 5000;
	/*
	 * Atributo que representa la ip
	 */
	private String ip;
	/*
	 * Atributo de la clase Socket necesaria para la conexi�n
	 */
	private Socket socket;
	
	/*
	 * M�todo constructor de la clase
	 */
	private TPCConnection() {
		
	}
	
	/*
	 * M�todo que devuelve la instancia de la clase
	 */
	public static TPCConnection getInstance() {
		if(instance == null) {
			instance = new TPCConnection();
		}
	return instance;
	}
	
	/*
	 * M�todo que modifica la ip
	 * @Param: String ip que representa el valor a modificar
	 */
	public TPCConnection setIp (String ip) {
		instance.ip = ip;
		return instance;
	}
	
	/*
	 * M�todo que modifica el puerto
	 * @Param: int puerto que representa el valor a modificar
	 */
	public TPCConnection setPuerto (int puerto) {
		instance.puerto = puerto;
		return instance;
	}
	
	/*
	 * M�todo que inicia la conexi�n creando un nuevo socket con numero de ip y puerto
	 */
	public void conector() {
		
		try {
			System.out.println("Enviando Conexi�n...");
			socket = new Socket(ip, puerto);
			System.out.println("Conexi�n exitosa");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * M�todo que envia la informaci�n ingresada por el usuario para ser procesada por el servidor
	 * @Param: String mensaje que representa el mensaje a enviar al servidor
	 */
	public void send(String mensaje) {
		try {
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bufw = new BufferedWriter(osw);
			PrintWriter out = new PrintWriter(bufw);
			out.println(mensaje);
			out.flush();
			
			InputStream is =socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			InputStreamReader isr = new InputStreamReader(bis);
			BufferedReader bufReader = new BufferedReader(isr);
			
			System.out.print(bufReader.readLine());
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * Hilo que permanece pendiente de la informaci�n que envia el servidor
	 */
	public class Receptor extends Thread{
		
		/*
		 * Atributo de la clase socket
		 */
		Socket socket;
		
		/*
		 * Constructor del hilo
		 */
		public Receptor(Socket socket) {
			this.socket = socket;
			
		}
		@Override
		/*
		 * Metodo que inicia el hilo que permanece pendiente del mensaje que se recibe
		 * @see java.lang.Thread#run()
		 */
		public void run(){
			
			try {
				InputStream is =socket.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				InputStreamReader isr = new InputStreamReader(bis);
				BufferedReader bufReader = new BufferedReader(isr);
				
				while (true) {
					
			
					String linea = bufReader.readLine();
					if(linea !=null && !linea.equals("")) {
	
					}
					
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				
				// TODO: handle exception
			}
		}
		
	}
	
	
}
