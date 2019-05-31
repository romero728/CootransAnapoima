package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.mysql.jdbc.PreparedStatement;

public class SQLOwner {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection = conBD.connection();
	
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
			request = "document";
		}
		
		return request;
	}
	
	public ArrayList<String> listOwners() {
		ArrayList<String> alOwners = new ArrayList<String>();
		String sql1 = "SELECT * FROM propietarios";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				alOwners.add(res.getString("id_propietario") + "." + res.getString("nombre_propietario") + " " + res.getString("apellido_propietario"));
			}
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return infoOwner;
	}
	
	public String updateOwner(String id, String firstName, String lastName, String birthdate, String address, String city, String phone, String email) {
		String sql1 = "UPDATE propietarios SET nombre_propietario = '" + firstName + "', apellido_propietario = '" + lastName + "', fechanacimiento_propietario = '" + birthdate + "', direccion_propietario = '" + address + "', ciudad_propietario = '" + city + "', telefono_propietario = '" + phone + "', email_propietario = '" + email + "' WHERE id_propietario = '" + id + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				return "success";
			} else {
				return "error update";
			}					
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	public String deleteOwner(String id) {
		String sql1 = "DELETE FROM propietarios WHERE id_propietario = '" + id + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				return "success";
			} else {
				return "error delete";
			}					
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	String getTypeDocument(String typeDoc) {
		String sql1 = "SELECT id_tipodocumento FROM tipodocumento WHERE nombre_tipodocumento = '" + typeDoc + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				return res.getString("id_tipodocumento");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	String getTypeNameDocument(String idType) {
		String sql1 = "SELECT nombre_tipodocumento FROM tipodocumento WHERE id_tipodocumento = '" + idType + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				return res.getString("nombre_tipodocumento");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	boolean validDocument(String doc) {
		String sql1 = "SELECT count(*) FROM propietarios WHERE documento_propietario = '" + doc + "'";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql1);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				if(res.getString("count(*)").equals("0")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return false;
	}
}
