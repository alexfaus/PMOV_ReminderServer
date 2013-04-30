package org.pmov.reminderapp.server;

public interface RecordatorioInterfaz {
	public void metodoPOST(String fecha, String asunto);
	
	public String metodoGET(String argumentos);
	
	public String metodoPUT(String argumentos);
	
	public String metodoDELETE(String argumentos);
	
	public void CerrarConexion();
	
}
