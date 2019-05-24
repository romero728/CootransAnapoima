package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class SQLOwner {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection = conBD.connection();
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertOwner(String firstName, String lastName, String typeDocument, String document, String birthdate, String address, String city, String phone, String email) {
		boolean validDocument = validDocument(document);		
		
		if(validDocument) {
			int iTypeDocument = Integer.parseInt(getTypeDocument(typeDocument));
			
			sql = "INSERT INTO propietarios(nombre_propietario, apellido_propietario, tipodocumento_propietario, documento_propietario, fechanacimiento_propietario, direccion_propietario, ciudad_propietario, telefono_propietario, email_propietario) VALUES ('" + firstName + "', '" + lastName + "', '" + iTypeDocument + "', '" + document + "', '" + birthdate + "', '" + address + "', '" + city + "', '" + phone + "', '" + email + "')";
			
			try {
				prep = (PreparedStatement) connection.prepareStatement(sql);
				int i = prep.executeUpdate();
				
				if(i > 0) {
					return "success";
				} else {
					return "error insert";
				}					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
