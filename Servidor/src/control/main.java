package control;

/*
 * Clase main
 * Fecha: 31/1/2019
 * Autores: Alejandra González - Angie Valentina Córdoba
*/
public class main {
	public static void main(String[] args) {
		
		/**
		 * Se intancia la clase TCPConnection y se establece el puerto a utilizar
		 */
		TCPConnection conexion = TCPConnection.getInstance().setPuerto(5000);
		/*
		 * Se llama al método esperarConexión de la clase TCPConnection
		 */
		conexion.esperarConexion();
		/*
		 * Se llama al método activarRecepcion de la clase TCPConnection
		 */
		conexion.activarReception();
		
	}
}
