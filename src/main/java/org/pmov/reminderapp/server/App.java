package org.pmov.reminderapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
	public static final int PUERTO = 1234;
	
    public static void main( String[] args ) throws IOException {
    	// Declaro un ServerSocket y un Socket
        ServerSocket socketServidor;
        Socket socketConectado;
        RecordatorioInterfaz a_la_BD;
        
        // Nuevo ServerSocket
        socketServidor = new ServerSocket(PUERTO);
        a_la_BD = new ConexionBD();
        
        // Creamos un while para estar leyendo las conexiones que puedan hacerse
        while(true){
        	// Acepto una conexión y espero
        	socketConectado = socketServidor.accept();
        	
        	// Mostramos mensaje de quién se ha conectado
        	// ++ CODE HERE ++
        	
        	// Creamos un nuevo hilo
        	new HiloServidor(socketConectado, a_la_BD);
        }        
    }
}