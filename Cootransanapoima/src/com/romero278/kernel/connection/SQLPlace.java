package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLPlace {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertPlace(String name) {
		String request = "";
		boolean valid = validName(name);
		
		if(valid) {
			sql = "INSERT INTO lugares(nombre_lugar) VALUES ('" + name + "')";
			
			try {
				connection = conBD.connection();
				prep = (PreparedStatement) connection.prepareStatement(sql);
				int i = prep.executeUpdate();
				
				if(i > 0) {
					request = "success";
				} else {
					request = "error insert";
				}
				
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			request = "name";
		}
		
		return request;
	}
	
	public ArrayList<String> listPlaces() {
		ArrayList<String> alPlaces = new ArrayList<String>();
		sql = "SELECT id_lugar, nombre_lugar FROM lugares ORDER BY id_lugar ASC";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				alPlaces.add(res.getString("id_lugar") + "." + res.getString("nombre_lugar"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alPlaces;
	}
	
	public String selectPlace(String id) {
		String infoPlace = "";
		sql = "SELECT * FROM lugares WHERE id_lugar = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				infoPlace += res.getString("id_lugar") + "|";
				infoPlace += res.getString("nombre_lugar") + "|";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
		return infoPlace;
	}
	
	public String updatePlace(String id, String name) {
		String request = "";
		
		sql = "UPDATE lugares SET nombre_lugar = '" + name + "' WHERE id_lugar = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request = "success";
			} else {
				request = "error update";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	public String deletePlace(String id) {
		String request1 = "", request2 = "", request3 = "", request4 = "", idRuta = "";
		
		String sql = "SELECT id_ruta FROM rutas WHERE lugardestino_ruta = '" + id + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				idRuta = res.getString("id_ruta");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String sql1 = "DELETE FROM recorridos WHERE ruta_recorrido = '" + idRuta + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql1);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request1 = "success";
			} else {
				request1 = "error delete";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String sql2 = "DELETE FROM ruta_tipovehiculo where idruta_rutatipovehiculo = '" + idRuta + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql2);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request2 = "success";
			} else {
				request2 = "error delete";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String sql3 = "DELETE FROM rutas where id_ruta = '" + idRuta + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql3);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request3 = "success";
			} else {
				request3 = "error delete";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String sql4 = "DELETE FROM lugares WHERE id_lugar = '" + id + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql4);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request4 = "success";
			} else {
				request4 = "error delete";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(request1.equals("success") && request2.equals("success") && request3.equals("success") && request4.equals("success")) {
			return "success";
		} else {
			return "error delete";
		}
	}
	
	boolean validName(String name) {
		boolean valid = false;
		
		sql = "SELECT count(*) FROM lugares WHERE nombre_lugar = '" + name + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				if(res.getString("count(*)").equals("0")) {
					valid = true;
				} else {
					valid = false;
				}
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return valid;
	}
}
