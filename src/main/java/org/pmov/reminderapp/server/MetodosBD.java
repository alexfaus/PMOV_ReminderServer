package org.pmov.reminderapp.server;

public class MetodosBD
{
	public static String metodoPOST(String argumentos)
	{
		return "OK POST CON CONSULTA > " + argumentos;
	}
	public static String metodoGET(String argumentos)
	{
		return "OK GET CON CONSULTA > " + argumentos;
	}
	
	public static String metodoPUT(String argumentos)
	{
		return "OK PUT CON CONSULTA > " + argumentos;
	}
	
	public static String metodoDELETE(String argumentos)
	{
		return "OK DELETE CON CONSULTA > " + argumentos;
	}
}
