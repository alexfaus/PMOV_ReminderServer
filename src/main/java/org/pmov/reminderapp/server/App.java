package org.pmov.reminderapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
	// Variable final con el n�mero de PUERTO de comunicaciones
	public static final int PUERTO = 1234;
	
    public static void main( String[] args ) throws IOException
    {
    	// Declaro un ServerSocket y un Socket
        ServerSocket socketServidor;
        Socket socketConectado;
        
        // Nuevo ServerSocket
        socketServidor = new ServerSocket(PUERTO);
        
        // Creamos un while para estar leyendo las conexiones que puedan hacerse
        while(true)
        {
        	// Acepto una conexi�n y espero
        	socketConectado = socketServidor.accept();
        	
        	// Mostramos mensaje de qui�n se ha conectado
        	// ++ CODE HERE ++
        	
        	// Creamos un nuevo hilo
        	new HiloServidor(socketConectado);
        }
    }
}

