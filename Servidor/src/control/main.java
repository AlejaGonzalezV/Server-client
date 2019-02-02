package control;

/*
 * Clase main
 * Fecha: 31/1/2019
 * Autores: Alejandra Gonz�lez - Angie Valentina C�rdoba
*/
public class main {
	public static void main(String[] args) {
		
		/**
		 * Se intancia la clase TCPConnection y se establece el puerto a utilizar
		 */
		TCPConnection conexion = TCPConnection.getInstance().setPuerto(5000);
		/*
		 * Se llama al m�todo esperarConexi�n de la clase TCPConnection
		 */
		conexion.esperarConexion();
		/*
		 * Se llama al m�todo activarRecepcion de la clase TCPConnection
		 */
		conexion.activarReception();
		
	}
}
