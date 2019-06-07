package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLTour {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection = conBD.connection();
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertTour(String route, String day, String hour, String type) {
		String request = "";
		boolean valid = validTour(route, day, hour);
		
		if(valid) {
			sql = "INSERT INTO recorridos(ruta_recorrido, dia_recorrido, hora_recorrido, tipovehiculo_recorrido) VALUES ('" + route + "', '" + day + "', '" + hour + "', '" + type + "')";
			
			try {
				prep = (PreparedStatement) connection.prepareStatement(sql);
				int i = prep.executeUpdate();
				
				if(i > 0) {
					request = "success";
				} else {
					request = "error insert";
				}					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			request = "equals";
		}
		
		return request;
	}
	
	public ArrayList<String> listTours(String day, String route) {
		ArrayList<String> alTours = new ArrayList<String>();
		
		sql = "SELECT id_recorrido, hora_recorrido FROM recorridos WHERE dia_recorrido = '" + day + "' and ruta_recorrido = '" + route + "' ORDER BY hora_recorrido ASC";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alTours.add(res.getString("id_recorrido") + "|" + res.getString("hora_recorrido"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alTours;
	}
	
	public String selectTour(String id) {
		String request = "";
		sql = "SELECT * FROM recorridos WHERE id_recorrido = '" + id + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				request = res.getString("id_recorrido") + "|" + getNameRoute(res.getString("ruta_recorrido")) + "|" + getNameDay(res.getString("dia_recorrido")) + "|" + res.getString("hora_recorrido") + "|" + getNameTypeVehicle(res.getString("tipovehiculo_recorrido"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	public String updateTour(String id, String route, String day, String hour, String type) {
		String request = "";
		boolean valid = validTour(route, day, hour);
		
		if(valid) {
			sql = "UPDATE recorridos SET ruta_recorrido = '" + route + "', dia_recorrido = '" + day + "', hora_recorrido = '" + hour + "', tipovehiculo_recorrido = '" + type + "' WHERE id_recorrido = '" + id + "'";
			
			try {
				prep = (PreparedStatement) connection.prepareStatement(sql);
				int i = prep.executeUpdate();
				
				if(i > 0) {
					request = "success";
				} else {
					request = "error update";
				}					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			request = "equals";
		}
		
		return request;
	}
	
	public String deleteTour(String id) {
		String request = "";
		sql = "DELETE FROM recorridos WHERE id_recorrido = '" + id + "'";
		
		System.out.println(sql);
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request = "success";
			} else {
				request = "error delete";
			}					
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	boolean validTour(String route, String day, String hour) {
		boolean valid = false;
		
		String sql = "SELECT count(*) FROM recorridos WHERE ruta_recorrido = '" + route + "'and dia_recorrido = '" + day + "'and hora_recorrido = '" + hour + "'";
		
		try {
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				if(res.getString("count(*)").equals("0")) {
					valid = true;
				} else {
					valid = false;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return valid;
	}
	
	String getNameRoute(String id) {
		String request = "";
		String sql = "SELECT lugardestino_ruta FROM rutas WHERE id_ruta = '" + id + "'";
		
		try {
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				String sql2 = "SELECT nombre_lugar FROM lugares WHERE id_lugar = '" + res.getString("lugardestino_ruta") + "'";
				
				try {
					PreparedStatement prep2 = (PreparedStatement) connection.prepareStatement(sql2);
					ResultSet res2 = (ResultSet) prep2.executeQuery();
					
					while(res2.next()) { 
						request = res2.getString("nombre_lugar");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	String getNameDay(String id) {
		String request = "";
		String sql = "SELECT nombre_dia FROM dias WHERE id_dia = '" + id + "'";
		
		try {
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				request = res.getString("nombre_dia");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	String getNameTypeVehicle(String id) {
		String request = "";
		String sql = "SELECT nombre_tipovehiculo FROM tipovehiculo WHERE id_tipovehiculo = '" + id + "'";
		
		try {
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				request = res.getString("nombre_tipovehiculo");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
}
