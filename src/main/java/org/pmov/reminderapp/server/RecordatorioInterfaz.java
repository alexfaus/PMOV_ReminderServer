package org.pmov.reminderapp.server;

public interface RecordatorioInterfaz {
	public String metodoPOST(String fecha, String asunto);
	
	public String metodoGET(String fecha);
	
	public String metodoDELETE(String fecha, String asunto);
	
}
