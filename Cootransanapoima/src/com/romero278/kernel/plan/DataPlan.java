package com.romero278.kernel.plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.romero278.kernel.connection.ConnectionBD;

public class DataPlan {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public ArrayList<ArrayList<String>> getActiveMobiles() {
		ArrayList<ArrayList<String>> alMobiles = new ArrayList<ArrayList<String>>();		
		
		sql = "SELECT id_movil, tipo_movil FROM moviles WHERE activo_movil = 1 ORDER BY id_movil ASC";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				ArrayList<String> current = new ArrayList<>();
				
				current.add(res.getString("id_movil"));
				current.add(res.getString("tipo_movil"));
				
				alMobiles.add(current);
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alMobiles;
	}
	
	public ArrayList<ArrayList<String>> getTours() {
		ArrayList<ArrayList<String>> alTours = new ArrayList<>();
		
		for(int i = 0; i < 7; i++) {
			sql = "SELECT * FROM recorridos WHERE dia_recorrido = '" + (i+1) + "' ORDER BY hora_recorrido ASC";
			
			try {
				connection = conBD.connection();
				prep = (PreparedStatement) connection.prepareStatement(sql);
				res = (ResultSet) prep.executeQuery();
				
				while(res.next()) {
					ArrayList<String> current = new ArrayList<>();
					
					current.add(res.getString("id_recorrido"));
					current.add(res.getString("ruta_recorrido"));
					current.add(res.getString("dia_recorrido"));
					current.add(res.getString("hora_recorrido"));
					
					alTours.add(current);
				}
				
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return alTours;
	}
	
	public ArrayList<String> getTypeRoute(String idRoute) {
		ArrayList<String> alType = new ArrayList<>();
		
		sql = "SELECT idtipo_rutatipovehiculo FROM ruta_tipovehiculo WHERE idruta_rutatipovehiculo = '" + idRoute + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alType.add(res.getString("idtipo_rutatipovehiculo"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alType;
	}
}
