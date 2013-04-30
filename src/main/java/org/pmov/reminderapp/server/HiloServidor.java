package org.pmov.reminderapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloServidor implements Runnable {
	// Socket donde estamos conectados
	private Socket elSocket;
	private RecordatorioInterfaz a_la_BD;

	// Stream de recepci�n
	private InputStream entrada;
	// Stream de env�o
	private OutputStream salida;

	// Mensaje confirmaci�n base datos > ok
	private String mensajeConfirmacion = "PROBLEMAS";

	public HiloServidor(Socket con, RecordatorioInterfaz BD)
			throws java.io.IOException {
		// Guardo el socket que me env�a la clase App
		this.elSocket = con;
		this.a_la_BD = BD;

		// Obtengo los streamings de recepci�n y env�o
		this.entrada = con.getInputStream();
		this.salida = con.getOutputStream();

		// Creamos un Thread para que ejecute el m�todo run()
		Thread th = new Thread(this);
		th.start();
	}

	public void run() {
		try {

			// Obtenemos el flujo de recepci�n
			String mensajeRecibidoSinTrocear = IO.leeLinea(entrada);

			// Troceamos la l�nea con split()
			String[] mensajeRecibidoTroceado = mensajeRecibidoSinTrocear.split(
					"\\s", 3);

			// Clasificamos los mensajes recibidos y llamamos a los m�todos
			if (mensajeRecibidoTroceado[0].equals("POST")) {

				a_la_BD.metodoPOST(mensajeRecibidoTroceado[1],
						mensajeRecibidoTroceado[2]);

			} else if (mensajeRecibidoTroceado[0].equals("GET")) {

				mensajeConfirmacion = a_la_BD
						.metodoGET(mensajeRecibidoTroceado[1]);

			} else if (mensajeRecibidoTroceado[0].equals("PUT")) {

				mensajeConfirmacion = a_la_BD
						.metodoPUT(mensajeRecibidoTroceado[1]);

			} else if (mensajeRecibidoTroceado[0].equals("DELETE")) {

				mensajeConfirmacion = a_la_BD
						.metodoDELETE(mensajeRecibidoTroceado[1]);

			}

			// Mandamos un mensaje al cliente como mensaje recibido
			IO.escribeLinea(mensajeConfirmacion, salida);

			// Cerramos los flujos
			entrada.close();
			salida.close();

			// Cerramos el Socket
			elSocket.close();
			a_la_BD.CerrarConexion();
			// Terminamos
			return;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
