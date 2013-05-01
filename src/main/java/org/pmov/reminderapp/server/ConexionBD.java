package org.pmov.reminderapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConexionBD implements RecordatorioInterfaz {

	// Esta variable es para guardar el POOL de conexiones de manera global
	DataSource dataSource = null;

	public ConexionBD() {
		// Creamos un POOL de conexiones, varios metodos pueden tener conexion a la vez
		// cuando terminan la conexion .close() y la dejan libre para otro metodo.
		// Se puede configurar el numero de conex. entre otras cosas
		// http://www.chuidiang.com/java/mysql/BasicDataSource-Pool-Conexiones.php
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUsername("testuser");
		basicDataSource.setPassword("test123");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/recordatorio");

		// Opcional. Sentencia SQL que le puede servir a BasicDataSource
		// para comprobar que la conexion es correcta.
		// basicDataSource.setValidationQuery("select 1");

		dataSource = basicDataSource;

	}

	public synchronized String metodoPOST(String fecha, String asunto) {
		Connection con = null;
		PreparedStatement pst = null;
		System.out.print("Posteamos: " + fecha + " " + asunto + "\n");
		try {
			con = dataSource.getConnection();
			pst = con.prepareStatement("INSERT INTO usuario VALUES(?,?)");
			pst.setString(1, fecha);
			pst.setString(2, asunto);
			pst.executeUpdate();
		} catch (SQLException ex) {
			// Aqui poner que pasa en caso de error
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				// Aqui poner que pasa en caso de error
			}
		}
		return "OK";
	}

	public synchronized String metodoGET(String fecha) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		System.out.print("Consulta: " + fecha + "\n");
		String leido = "ERROR"; // Pongo ERROR porque sino devuelve NULL, se
								// puede imple. compro.
		try {

			con = dataSource.getConnection();

			pst = con
					.prepareStatement("SELECT asunto FROM usuario WHERE fecha = ?");
			pst.setString(1, fecha);
			rs = pst.executeQuery();

			while (rs.next()) {
				leido = rs.getString(1); // Se queda con la ultima entrada de la
											// tabla
			}
		} catch (SQLException ex) {
			// Aqui poner que pasa en caso de error
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				// Aqui poner que pasa en caso de error
			}
		}
		return leido;
	}

	public String metodoPUT(String argumentos) {
		return "OK PUT CON CONSULTA > " + argumentos;
	}

	public String metodoDELETE(String argumentos) {
		return "OK DELETE CON CONSULTA > " + argumentos;
	}
}
