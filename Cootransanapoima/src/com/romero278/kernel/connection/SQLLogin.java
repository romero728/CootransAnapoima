package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class SQLLogin {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	public boolean login(String company, String password) {
		String sql = "SELECT count(*) FROM empresas WHERE nombre_empresa = '" + company + "' and password_empresa = '" + password + "'";
		
		try {
			connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				if(res.getString("count(*)").equals("0")) {					
					return false;
				} else {
					return true;
				}
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return false;
	}
}
