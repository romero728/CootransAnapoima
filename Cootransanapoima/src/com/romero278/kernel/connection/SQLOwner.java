package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.mysql.jdbc.PreparedStatement;

public class SQLOwner {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertOwner(String firstName, String lastName, String typeDocument, String document, String birthdate, String address, String city, String phone, String email) {
		String request = "";
		boolean validDocument = validDocument(document);		
		
		if(validDocument) {
			int iTypeDocument = Integer.parseInt(getTypeDocument(typeDocument));
			
			sql = "INSERT INTO propietarios(nombre_propietario, apellido_propietario, tipodocumento_propietario, documento_propietario, fechanacimiento_propietario, direccion_propietario, ciudad_propietario, telefono_propietario, email_propietario) VALUES ('" + firstName + "', '" + lastName + "', '" + iTypeDocument + "', '" + document + "', '" + birthdate + "', '" + address + "', '" + city + "', '" + phone + "', '" + email + "')";
			
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
			request = "document";
		}
		
		return request;
	}
	
	public ArrayList<String> listOwners() {
		ArrayList<String> alOwners = new ArrayList<String>();
		String sql1 = "SELECT * FROM propietarios ORDER BY id_propietario ASC";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				alOwners.add(res.getString("id_propietario") + "." + res.getString("nombre_propietario") + " " + res.getString("apellido_propietario"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		
		return alOwners;
	}
	
	public String selectOwner(String idO) {
		idO.replace('.', '|');
		String[] parts = idO.split(Pattern.quote("|"));
		String id = parts[0];
		String infoOwner = "";
		String sql1 = "SELECT * FROM propietarios WHERE id_propietario = '" + id + "'";
		String nom, ape, td, doc, fec, dir, ci, tel, em;		
		
		try {
			connection = conBD.connection();
			PreparedStatement preps = (PreparedStatement) connection.prepareStatement(sql1);
			ResultSet ress = (ResultSet) preps.executeQuery();
			
			while(ress.next()) {
				td = getTypeNameDocument(ress.getString("tipodocumento_propietario"));
				nom = ress.getString("nombre_propietario");
				ape = ress.getString("apellido_propietario");
				doc = ress.getString("documento_propietario");
				fec = ress.getString("fechanacimiento_propietario");
				dir = ress.getString("direccion_propietario");
				ci = ress.getString("ciudad_propietario");
				tel = ress.getString("telefono_propietario");
				em = ress.getString("email_propietario");
				
				infoOwner = id + "|" + nom + "|" + ape + "|" + td + "|" + doc + "|" + fec + "|" + dir + "|" + ci + "|" + tel + "|" + em + "|";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return infoOwner;
	}
	
	public String updateOwner(String id, String firstName, String lastName, String birthdate, String address, String city, String phone, String email) {
		String request = "";
		String sql1 = "UPDATE propietarios SET nombre_propietario = '" + firstName + "', apellido_propietario = '" + lastName + "', fechanacimiento_propietario = '" + birthdate + "', direccion_propietario = '" + address + "', ciudad_propietario = '" + city + "', telefono_propietario = '" + phone + "', email_propietario = '" + email + "' WHERE id_propietario = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql1);
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
	
	public String deleteOwner(String id) {
		String request = "", request1 = "";
		
		String sql = "DELETE FROM moviles WHERE propietario_movil = '" + id + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request = "success";
			} else {
				request = "error delete";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		String sql1 = "DELETE FROM propietarios WHERE id_propietario = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql1);
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
		
		if(request.equals("success") && request1.equals("success")) {
			return "success";
		} else {
			return "error delete";
		}
	}
	
	String getTypeDocument(String typeDoc) {
		String request = "";
		String sql1 = "SELECT id_tipodocumento FROM tipodocumento WHERE nombre_tipodocumento = '" + typeDoc + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				request = res.getString("id_tipodocumento");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	String getTypeNameDocument(String idType) {
		String request = "";
		String sql1 = "SELECT nombre_tipodocumento FROM tipodocumento WHERE id_tipodocumento = '" + idType + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				request = res.getString("nombre_tipodocumento");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	boolean validDocument(String doc) {
		boolean valid = true;
		String sql1 = "SELECT count(*) FROM propietarios WHERE documento_propietario = '" + doc + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql1);
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
