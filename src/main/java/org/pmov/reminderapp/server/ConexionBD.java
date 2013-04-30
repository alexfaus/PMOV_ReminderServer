package org.pmov.reminderapp.server;
public class ConexionBD implements RecordatorioInterfaz {
	
	public String metodoPOST(String argumentos){
		return "OK POST CON CONSULTA > " + argumentos;
	}
	
	public String metodoGET(String argumentos){
		return "OK GET CON CONSULTA > " + argumentos;
	}
	
	public String metodoPUT(String argumentos){
		return "OK PUT CON CONSULTA > " + argumentos;
	}
	
	public String metodoDELETE(String argumentos){
		return "OK DELETE CON CONSULTA > " + argumentos;
	}
}