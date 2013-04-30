package org.pmov.reminderapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloServidor implements Runnable {
	// Socket donde estamos conectados
	private Socket elSocket;
	private RecordatorioInterfaz a_la_BD;

	// Stream de recepción
	private InputStream entrada;
	// Stream de envío
	private OutputStream salida;

	// Mensaje confirmación base datos > ok
	private String mensajeConfirmacion = "PROBLEMAS";

	public HiloServidor(Socket con, RecordatorioInterfaz BD)
			throws java.io.IOException {
		// Guardo el socket que me envía la clase App
		this.elSocket = con;
		this.a_la_BD = BD;

		// Obtengo los streamings de recepción y envío
		this.entrada = con.getInputStream();
		this.salida = con.getOutputStream();

		// Creamos un Thread para que ejecute el método run()
		Thread th = new Thread(this);
		th.start();
	}

	public void run() {
		try {

			// Obtenemos el flujo de recepción
			String mensajeRecibidoSinTrocear = IO.leeLinea(entrada);

			// Troceamos la línea con split()
			String[] mensajeRecibidoTroceado = mensajeRecibidoSinTrocear.split(
					"\\s", 3);

			// Clasificamos los mensajes recibidos y llamamos a los métodos
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
