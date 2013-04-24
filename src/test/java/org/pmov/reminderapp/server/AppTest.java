package org.pmov.reminderapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }
    
    public static Test suite()
    {
    	// Creamos un thread
    	Thread th = new Thread(new Runnable()
    	{
    		public void run()
    		{
    			try
    			{
    				App.main(null);
    			} catch(IOException ex)
    			{
    				
    			}
    		}
    	});
    	
    	th.start();
        return new TestSuite( AppTest.class );
    }

    public void testApp() throws UnknownHostException, IOException
    {
    	String hostServidor = "localhost";
		int PUERTO = 1234;
		
		Socket elSocket = new Socket(hostServidor, PUERTO);
		OutputStream salida;
		InputStream entrada;
		salida = elSocket.getOutputStream();
		entrada = elSocket.getInputStream();
		
		IO.escribeLinea("POST ordenesEnviadas", salida);
		
		String mensajeRecibido = IO.leeLinea(entrada);
		System.out.println(mensajeRecibido);
		
		salida.close();
		entrada.close();
		elSocket.close();
    }
}
