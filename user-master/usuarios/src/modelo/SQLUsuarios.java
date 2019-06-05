package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLUsuarios extends Conexion {

	public boolean registrar(Usuarios usr) {
		
		//consulta 
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		
		String sql = "INSERT INTO usuarios(usuario, password, nombre, correo, id_tipo) VALUES(?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			ps.setString(2, usr.getPassword());
			ps.setString(3, usr.getNombre());
			ps.setString(4, usr.getCorreo());
			ps.setInt(5, usr.getId_tipo());
			ps.execute();
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
			return false;
		}
		
	}
	
public int existeUsuario(String usuario) {
		
		//consulta 
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		
		String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
			return 1;
			
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
			return 1;
		}
		
	}
	
	
	
public boolean login(Usuarios usr) {
		
		//consulta 
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		
		String sql = "SELECT id, usuario, password, nombre, id_tipo FROM usuarios WHERE usuario = ?";		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				if(usr.getPassword().equals(rs.getString(3))) {
					
					String sqlUpdate = "UPDATE usuarios SET last_session = ? WHERE id=?";
					
					ps = con.prepareStatement(sqlUpdate);
					ps.setString(1, usr.getLas_session());
					ps.setInt(2, rs.getInt(1));
					ps.execute();
					
					usr.setId(rs.getInt(1));
					usr.setNombre(rs.getString(4));
					usr.setId_tipo(rs.getInt(5));
					return true;
					
				}else {
					return false;
				}
			}
			
			return false;
			
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
			return false;
		}
		
	}
	
public boolean esEmail(String correo) {
	//Patron para validar Email
	Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	
	//matcher realiza la validacion de arriba
	Matcher mather = pattern.matcher(correo);
	
	return mather.find();
}

}
