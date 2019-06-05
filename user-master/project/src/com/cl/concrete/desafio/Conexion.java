package com.cl.concrete.desafio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
	
	private final String base = "ejemplo";
	private final String user = "root";
	private final String password = "1234";
	private final String url = "jdbc:mysql://localhost:3306/ejemplo";
	private Connection con = null;
	
	public Connection getConexion() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			
			
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
		} catch(SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
		}
		return con;
	}
	
}

