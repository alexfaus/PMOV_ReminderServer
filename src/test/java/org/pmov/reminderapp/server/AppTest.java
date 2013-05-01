package org.pmov.reminderapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
     * Create the test case
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
    	// Creamos un thread
    	Thread th = new Thread(new Runnable(){
    		public void run() {
    			try {
    				App.main(null);
    			} catch(IOException ex) {
    				
    			}
    		}
    	});
    	th.start();
    	
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws UnknownHostException, IOException {
    	probamosPOST(); // Creamos entrada en la tabla (tiene que devolver OK?)
    	//for(int i=0;i<999999;i++);
    	probamosGET(); // Leemos la entrada anterior
    	//for(int i=0;i<999999;i++);
    	probamosDELETE();
    }
    
    public void probamosGET() throws UnknownHostException, IOException{
    	String hostServidor = "localhost";
		int PUERTO = 1234;
		
		Socket elSocket = new Socket(hostServidor, PUERTO);
		
		OutputStream salida;
		InputStream entrada;
		salida = elSocket.getOutputStream();
		entrada = elSocket.getInputStream();
		
		String mensajeEnviado = "GET 2014-01-01";
		// Consulta que enviamos al servidor
		System.out.println("" +
				"+----------------------+\n" +
				"| Consulta al servidor |> " + mensajeEnviado + "\n" +
				"+----------------------+");
		IO.escribeLinea(mensajeEnviado, salida);
		
		// Datos recibidos del servidor
		String mensajeRecibido = IO.leeLinea(entrada);
		System.out.println("" +
				"+----------------------+\n" +
				"| Mensaje del servidor |> " + mensajeRecibido + "\n" +
				"+----------------------+");
		
		// Cerramos flujos y socket
		salida.close();
		entrada.close();
		elSocket.close();
    }
    
    public void probamosPOST() throws UnknownHostException, IOException{
    	String hostServidor = "localhost";
		int PUERTO = 1234;
		
		Socket elSocket = new Socket(hostServidor, PUERTO);
		
		OutputStream salida;
		InputStream entrada;
		salida = elSocket.getOutputStream();
		entrada = elSocket.getInputStream();
		
		String mensajeEnviado = "POST 2014-01-01 Nos quedan 4 meses para terminar la carrera.";
		// Consulta que enviamos al servidor
		System.out.println("" +
				"+----------------------+\n" +
				"| Consulta al servidor |> " + mensajeEnviado + "\n" +
				"+----------------------+");
		IO.escribeLinea(mensajeEnviado, salida);

		// Datos recibidos del servidor
		String mensajeRecibido = IO.leeLinea(entrada);
		System.out.println("" +
				"+----------------------+\n" +
				"| Mensaje del servidor |> " + mensajeRecibido + "\n" +
				"+----------------------+");
		
		// Cerramos flujos y socket
		salida.close();
		entrada.close();
		elSocket.close();
    }
    
    public void probamosDELETE() throws UnknownHostException, IOException{
    	String hostServidor = "localhost";
		int PUERTO = 1234;
		
		Socket elSocket = new Socket(hostServidor, PUERTO);
		
		OutputStream salida;
		InputStream entrada;
		salida = elSocket.getOutputStream();
		entrada = elSocket.getInputStream();
		
		String mensajeEnviado = "DELETE 2014-01-01 Nos quedan 4 meses para terminar la carrera.";
		// Consulta que enviamos al servidor
		System.out.println("" +
				"+----------------------+\n" +
				"| Consulta al servidor |> " + mensajeEnviado + "\n" +
				"+----------------------+");
		IO.escribeLinea(mensajeEnviado, salida);
		
		// Datos recibidos del servidor
		String mensajeRecibido = IO.leeLinea(entrada);
		System.out.println("" +
				"+----------------------+\n" +
				"| Mensaje del servidor |> " + mensajeRecibido + "\n" +
				"+----------------------+");
		
		// Cerramos flujos y socket
		salida.close();
		entrada.close();
		elSocket.close();
    }
    
    
}
