package com.romero278.kernel.plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.romero278.kernel.connection.ConnectionBD;

public class DataPlan {
	ConnectionBD conBD = new ConnectionBD();
//	Connection connection;
//	
//	String sql;
//	PreparedStatement prep;
//	ResultSet res;
	
	public ArrayList<ArrayList<String>> getActiveMobiles() {
		ArrayList<ArrayList<String>> alMobiles = new ArrayList<ArrayList<String>>();		
		
		String sql = "SELECT numero_movil, tipo_movil FROM moviles WHERE activo_movil = 1 ORDER BY id_movil ASC";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				ArrayList<String> current = new ArrayList<>();
				
				current.add(res.getString("numero_movil"));
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
			String sql = "SELECT * FROM recorridos WHERE dia_recorrido = '" + (i+1) + "' ORDER BY hora_recorrido ASC";
			
			try {
				Connection connection = conBD.connection();
				PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
				ResultSet res = (ResultSet) prep.executeQuery();
				
				while(res.next()) {
					ArrayList<String> current = new ArrayList<>();
					
					String sql1 = "SELECT activo_ruta FROM rutas WHERE id_ruta = '" + res.getString("ruta_recorrido") + "'";
					
					Connection connection1 = conBD.connection();
					PreparedStatement prep1 = (PreparedStatement) connection1.prepareStatement(sql1);
					ResultSet res1 = (ResultSet) prep1.executeQuery();
					
					while(res1.next()) {
						if(res1.getString("activo_ruta").equals("1")) {
							current.add(res.getString("id_recorrido"));
							current.add(res.getString("ruta_recorrido"));
							current.add(res.getString("dia_recorrido"));
							current.add(res.getString("hora_recorrido"));
							
							alTours.add(current);
						}
					}
					
					connection1.close();
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
		
		String sql = "SELECT idtipo_rutatipovehiculo FROM ruta_tipovehiculo WHERE idruta_rutatipovehiculo = '" + idRoute + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alType.add(res.getString("idtipo_rutatipovehiculo"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alType;
	}
	
	public String getCompleteNameOwner(String numMobile) {
		String request = "";
		
		Connection connection = conBD.connection();
		String sql = "SELECT propietario_movil FROM moviles WHERE numero_movil = '" + numMobile + "'";
		try {
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				String sql1 = "SELECT nombre_propietario, apellido_propietario FROM propietarios WHERE id_propietario = '" + res.getString("propietario_movil") + "'";
				
				PreparedStatement prep1 = (PreparedStatement) connection.prepareStatement(sql1);
				ResultSet res1 = (ResultSet) prep1.executeQuery();
				
				while(res1.next()) {
					request = res1.getString("apellido_propietario") + " " + res1.getString("nombre_propietario");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return request;
	}
}
