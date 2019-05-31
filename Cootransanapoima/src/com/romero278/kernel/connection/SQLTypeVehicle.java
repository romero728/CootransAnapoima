package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class SQLTypeVehicle {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection = conBD.connection();
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public ArrayList<String> selectTypeVehicles() {
		ArrayList<String> alTypeVehicle = new ArrayList<String>();
		sql = "SELECT nombre_tipovehiculo FROM tipovehiculo";
		
		try {
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alTypeVehicle.add(res.getString("nombre_tipovehiculo"));
		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alTypeVehicle;
	}
}
