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
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Clase TCPConnection
 * Fecha: 31/1/2019
 * Autores: Alejandra Gonz�lez - Angie Valentina C�rdoba
*/
public class TCPConnection {

	/*
	 * Atributo que representa una instancia de la clase TPCConnection
	 */
	private static TCPConnection instance;
	/*
	 * Atributo que representa el puerto a utilizar
	 */
	private int puerto;
	/*
	 * Atributo de la clase Socket necesaria para la conexi�n
	 */
	private Socket socket;
	/*
	 * Atributo de la clase Receptor necesario para recibir la informaci�n
	 */
	private Receptor receptor;

	/*
	 * M�todo constructor de la clase
	 */
	private TCPConnection() {
		
	}
	
	/*
	 * M�todo que devuelve la instancia de la clase
	 */
	public static TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	/*
	 * M�todo que modifica el puerto
	 * @Param: int puerto que representa el valor a modificar
	 */
	public TCPConnection setPuerto(int puerto) {
		
		instance.puerto = puerto;
		
		
		return instance;
	}
	
	/*
	 * M�todo que inicializa la conexi�n con el usuario
	 */
	public void esperarConexion() {
		try {
			
			System.out.println("Esperando conexion.....");
			ServerSocket server = new ServerSocket(puerto);
			socket = server.accept();
			receptor = new Receptor(socket);
			
			System.out.println("Conexion establecida");
			
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	/*
	 * M�todo que env�a la respuesta al usuario
	 */
	public void send(String mensaje){
		try {
			System.out.println("Enviando mensaje...");
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(osw);
			pw.println(mensaje);
			pw.flush();
			System.out.println("Encriptaci�n devuelta");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo que inicializa el hilo responsable de procesar la informaci�n
	 */
	public void activarReception() {
		receptor.start();
	}

	/*
	 * Hilo que procesa la informaci�n enviada por el usuario y encripta el mensaje
	 */
	public class Receptor extends Thread{
		
		/*
		 * Atributo de tipo Socket
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
		 * M�todo que inicia el hilo y encripta el mensaje
		 * (non-Javadoc)
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
						
						String procesado = "";
						
						//AQU� HACER LO DE LA ENCRIPTACI�N
						
						StringBuilder cifrado = new StringBuilder();
				        for (int i = 0; i < linea.length(); i++) {
				            if (linea.charAt(i) >= 'a' && linea.charAt(i) <= 'z') {
				                if ((linea.charAt(i) + 2) > 'z') {
				                    cifrado.append((char) (linea.charAt(i) + 2 - 26));
				                } else {
				                    cifrado.append((char) (linea.charAt(i) + 2));
				                }
				            } else if (linea.charAt(i) >= 'A' && linea.charAt(i) <= 'Z') {
				                if ((linea.charAt(i) + 2) > 'Z') {
				                    cifrado.append((char) (linea.charAt(i) + 2 - 26));
				                } else {
				                    cifrado.append((char) (linea.charAt(i) + 2));
				                }
				            }
				        }
				        procesado = cifrado.toString();
						
						send(procesado);
					
					}
					
					
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				
				// TODO: handle exception
			}
		}
		
	}
	
}
