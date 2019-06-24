package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLDay {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public ArrayList<String> listDays() {
		ArrayList<String> alDays = new ArrayList<String>();
		sql = "SELECT id_dia, nombre_dia FROM dias ORDER BY id_dia ASC";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alDays.add(res.getString("id_dia") + "|" + res.getString("nombre_dia"));		
			}
			
			connection.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alDays;
	}
}
