package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLMobile {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertMobile(String number, String owner, String brand, String model, String licensePlate, String capacity, String typeVehicle, String active) {
		String request = "";
		boolean valid = validNumber(number);
		
		if(valid) {
			String act;
			
			if(active.equals("Si")) {
				act = "1";
			} else {
				act = "0";
			}			
			
			sql = "INSERT INTO moviles(numero_movil, empresa_movil, propietario_movil, marca_movil, modelo_movil, placa_movil, capacidadpasajeros_movil, tipo_movil, activo_movil) VALUES ('" + number + "', '1', '" + owner + "', '" + brand + "', '" + model + "', '" + licensePlate + "', '" + capacity + "', '" + typeVehicle + "', '" + act + "')";
			
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
			request = "number";
		}
		
		return request;
	}
	
	public ArrayList<String> listMobiles() {
		ArrayList<String> alMobiles = new ArrayList<String>();
		sql = "SELECT numero_movil FROM moviles ORDER BY id_movil ASC";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				alMobiles.add(res.getString("numero_movil"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alMobiles;
	}
	
	public String selectMobile(String idMobile) {
		String infoMobile = "";
		
		sql = "SELECT * FROM moviles WHERE numero_movil = '" + idMobile + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				String nameCompany = getNameCompany(res.getString("empresa_movil"));
				String nameOwner = getNameOwner(res.getString("propietario_movil"));
				String nameType = getNameTypeVehicle(res.getString("tipo_movil"));
				String nameActive;
				
				if(res.getString("activo_movil").equals("1")) {
					nameActive = "Si";
				} else {
					nameActive = "No";
				}
				
				infoMobile += res.getString("id_movil") + "|";
				infoMobile += res.getString("numero_movil") + "|";
				infoMobile += nameCompany + "|";
				infoMobile += nameOwner + "|";
				infoMobile += res.getString("marca_movil") + "|";
				infoMobile += res.getString("modelo_movil") + "|";
				infoMobile += res.getString("placa_movil") + "|";
				infoMobile += res.getString("capacidadpasajeros_movil") + "|";
				infoMobile += nameType + "|";
				infoMobile += nameActive + "|";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return infoMobile;
	}
	
	public String updateMobile(String id, String number, String owner, String brand, String model, String licensePlate, String capacity, String typeVehicle, String active) {
		String request = "", act;
		
		if(active.equals("Si")) {
			act = "1";
		} else {
			act = "0";
		}
		
		sql = "UPDATE moviles SET numero_movil = '" + number + "', empresa_movil = '1', propietario_movil = '" + owner + "', marca_movil = '" + brand + "', modelo_movil = '" + model + "', placa_movil = '" + licensePlate + "', capacidadpasajeros_movil = '" + capacity + "', tipo_movil = '" + typeVehicle + "', activo_movil = '"+ act + "' WHERE id_movil = '" + id + "'";
		
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
	
	public String deleteMobile(String id) {
		String request = "";
		
		sql = "DELETE FROM moviles WHERE id_movil = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request = "success";
			} else {
				request = "error delete";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	boolean validNumber(String number) {
		boolean valid = false;
		
		sql = "SELECT count(*) FROM moviles WHERE numero_movil = '" + number + "'";
		
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
	
	String getNameCompany(String idCompany) {
		String nameCompany = "";
		
		String sql = "SELECT nombre_empresa FROM empresas WHERE id_empresa = '" + idCompany + "'";
		
		try {
			connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				nameCompany = res.getString("nombre_empresa");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return nameCompany;
	}
	
	String getNameOwner(String idOwner) {
		String nameOwner = "";
		
		String sql = "SELECT nombre_propietario, apellido_propietario FROM propietarios WHERE id_propietario = '" + idOwner + "'";
		
		try {
			connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				nameOwner = res.getString("nombre_propietario") + " " + res.getString("apellido_propietario");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return nameOwner;
	}
	
	String getNameTypeVehicle(String idType) {
		String nameType = ""; 
		
		String sql1 = "SELECT nombre_tipovehiculo FROM tipovehiculo WHERE id_tipovehiculo = '" + idType + "'";
		
		try {
			connection = conBD.connection();
			PreparedStatement prep1 = (PreparedStatement) connection.prepareStatement(sql1);
			ResultSet res1 = (ResultSet) prep1.executeQuery();
			
			while(res1.next()) {
				nameType = res1.getString("nombre_tipovehiculo");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return nameType;
	}
}
