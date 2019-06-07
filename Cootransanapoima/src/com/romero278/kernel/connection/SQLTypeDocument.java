package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLTypeDocument {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection = conBD.connection();
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public ArrayList<String> selectTypeDocuments() {
		ArrayList<String> alTypeDocument = new ArrayList<String>();
		sql = "SELECT nombre_tipodocumento FROM tipodocumento ORDER BY id_tipodocumento ASC";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alTypeDocument.add(res.getString("nombre_tipodocumento"));
		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alTypeDocument;
	}
	
}
