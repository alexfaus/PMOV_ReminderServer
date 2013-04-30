package org.pmov.reminderapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD implements RecordatorioInterfaz {

	// Estas variables global para no estar abriendo y cerrando
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	public ConexionBD() {
		// Parametros de la base de datos
		String url = "jdbc:mysql://localhost:3306/recordatorio";
		//String url = "jdbc:mysql://localhost:3306/recordatorio";
		String user = "testuser";
		String password = "test123";

		try {
			con = DriverManager.getConnection(url, user, password);
			//st = con.createStatement();
		} catch (SQLException ex) {
			// Aqui poner que pasa en caso de error
		}
	}

	public void metodoPOST(String fecha, String asunto) {
		System.out.print("Posteamos: " + fecha + " " + asunto + "\n");
		try {
			pst = con.prepareStatement("INSERT INTO usuario VALUES('2013-05-01','No hay clase')");
			//pst.setString(1, fecha);
			//pst.setString(2, asunto);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// Aqui poner que pasa en caso de error
		}
	}

	public String metodoGET(String fecha) {

		System.out.print("Consulta: " + fecha + "\n");
		String leido = "ERROR"; // Pongo ERROR porque sino devuelve NULL, se puede imple. compro.
		try {
			pst = con
					.prepareStatement("SELECT asunto FROM usuario WHERE fecha = ?");
			pst.setString(1, fecha);
			rs = pst.executeQuery();

			while (rs.next()) {
				leido = rs.getString(1); // Se queda con la ultima entrada de la tabla
			}
		} catch (SQLException ex) {
			// Aqui poner que pasa en caso de error
		}
		return leido;
	}

	public String metodoPUT(String argumentos) {
		return "OK PUT CON CONSULTA > " + argumentos;
	}

	public String metodoDELETE(String argumentos) {
		return "OK DELETE CON CONSULTA > " + argumentos;
	}

	public void CerrarConexion() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
			if (pst != null)
				pst.close();
		} catch (SQLException ex) {
			// Aqui poner que pasa en caso de error
		}
	}
}