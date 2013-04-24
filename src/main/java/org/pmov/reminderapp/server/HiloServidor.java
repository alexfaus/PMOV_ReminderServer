package org.pmov.reminderapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloServidor implements Runnable
{
//	Socket donde estamos conectados
	private Socket elSocket;
	
//	Stream de recepción
	private InputStream entrada;
	
//	Stream de envío
	private OutputStream salida;

	public HiloServidor(Socket con) throws java.io.IOException
	{
//		Guardo el socket que me envía la clase App
		this.elSocket = con;
		
//		Obtengo los streamings de recepción y envío
		this.entrada = con.getInputStream();
		this.salida = con.getOutputStream();
		
//		Creamos un Thread para que ejecute el método run()
		Thread th = new Thread(this);
		th.start();
	}
	public void run()
	{
		try
		{
//			Obtenemos el flujo de recepción
			String mensajeRecibidoSinTrocear = IO.leeLinea(entrada);
			
//			Troceamos la línea con split()
			String[] mensajeRecibidoTroceado = mensajeRecibidoSinTrocear.split(" ");
			
//			Comprobamos el troceado
			System.out.println("Trozo 0: " + mensajeRecibidoTroceado[0]);
			System.out.println("Trozo 1: " + mensajeRecibidoTroceado[1]);
			
//			Mandamos un mensaje al cliente como mensaje recibido
			IO.escribeLinea("Mensaje recibido", salida);
			
//			Cerramos los flujos
			entrada.close();
			salida.close();
			
//			Cerramos el Socket
			elSocket.close();
			
//			Terminamos
			return;
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
}
