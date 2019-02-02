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
 * Autores: Alejandra González - Angie Valentina Córdoba
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
	 * Atributo de la clase Socket necesaria para la conexión
	 */
	private Socket socket;
	/*
	 * Atributo de la clase Receptor necesario para recibir la información
	 */
	private Receptor receptor;

	/*
	 * Método constructor de la clase
	 */
	private TCPConnection() {
		
	}
	
	/*
	 * Método que devuelve la instancia de la clase
	 */
	public static TCPConnection getInstance() {
		if(instance == null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	/*
	 * Método que modifica el puerto
	 * @Param: int puerto que representa el valor a modificar
	 */
	public TCPConnection setPuerto(int puerto) {
		
		instance.puerto = puerto;
		
		
		return instance;
	}
	
	/*
	 * Método que inicializa la conexión con el usuario
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
	 * Método que envía la respuesta al usuario
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
			System.out.println("Encriptación devuelta");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Método que inicializa el hilo responsable de procesar la información
	 */
	public void activarReception() {
		receptor.start();
	}

	/*
	 * Hilo que procesa la información enviada por el usuario y encripta el mensaje
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
		 * Método que inicia el hilo y encripta el mensaje
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
						
						//AQUÍ HACER LO DE LA ENCRIPTACIÓN
						
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
