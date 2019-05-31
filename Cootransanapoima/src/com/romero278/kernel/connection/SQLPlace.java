package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLPlace {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection = conBD.connection();
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertPlace(String name) {
		String request = "";
		boolean valid = validName(name);
		
		if(valid) {
			sql = "INSERT INTO lugares(nombre_lugar) VALUES ('" + name + "')";
			
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
			request = "name";
		}
		
		return request;
	}
	
	public ArrayList<String> listPlaces() {
		ArrayList<String> alPlaces = new ArrayList<String>();
		sql = "SELECT id_lugar, nombre_lugar FROM lugares ORDER BY id_lugar ASC";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				alPlaces.add(res.getString("id_lugar") + "." + res.getString("nombre_lugar"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alPlaces;
	}
	
	public String selectPlace(String id) {
		String infoPlace = "";
		sql = "SELECT * FROM lugares WHERE id_lugar = '" + id + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				infoPlace += res.getString("id_lugar") + "|";
				infoPlace += res.getString("nombre_lugar") + "|";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
		return infoPlace;
	}
	
	public String updatePlace(String id, String name) {
		String request = "";
		
		sql = "UPDATE lugares SET nombre_lugar = '" + name + "' WHERE id_lugar = '" + id + "'";
		
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
		
		return request;
	}
	
	public String deletePlace(String id) {
		String request = "";
		
		sql = "DELETE FROM lugares WHERE id_lugar = '" + id + "'";
		
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
	
	boolean validName(String name) {
		boolean valid = false;
		
		sql = "SELECT count(*) FROM lugares WHERE nombre_lugar = '" + name + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
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
}
