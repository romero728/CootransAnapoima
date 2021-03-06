package com.romero278.kernel.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.mysql.jdbc.PreparedStatement;

public class SQLRoute {
	ConnectionBD conBD = new ConnectionBD();
	Connection connection;
	
	String sql;
	PreparedStatement prep;
	ResultSet res;
	
	public String insertRoute(String placeEnd, String duration, String active, String[] type) {
		String request = "", idRoute = "";
		boolean valid = validPlaceEnd(placeEnd);
		
		if(valid) {
			String act;
			
			if(active.equals("Si")) {
				act = "1";
			} else {
				act = "0";
			}
			
			sql = "INSERT INTO rutas(lugarorigen_ruta, lugardestino_ruta, duracion_ruta, activo_ruta) VALUES ('1', '" + placeEnd + "', '" + duration + "', '" + act + "')";
			
			try {
				connection = conBD.connection();
				prep = (PreparedStatement) connection.prepareStatement(sql);
				int i = prep.executeUpdate();
				
				if(i > 0) {
					sql = "SELECT last_insert_id()";
					
					prep = (PreparedStatement) connection.prepareStatement(sql);
					res = (ResultSet) prep.executeQuery();
					
					while(res.next()) {
						idRoute = res.getString("last_insert_id()");
					}
					
					if(type[0].equals("1")) {
						sql = "INSERT INTO ruta_tipovehiculo (idruta_rutatipovehiculo, idtipo_rutatipovehiculo) VALUES ('" + idRoute + "', '1')";
						
						prep = (PreparedStatement) connection.prepareStatement(sql);
						i = prep.executeUpdate();
						
						if(i > 0) {
							request = "success";
						} else {
							request = "error insert";
						}
					}
					
					if(type[1].equals("1")) {
						sql = "INSERT INTO ruta_tipovehiculo (idruta_rutatipovehiculo, idtipo_rutatipovehiculo) VALUES ('" + idRoute + "', '2')";
						
						prep = (PreparedStatement) connection.prepareStatement(sql);
						i = prep.executeUpdate();
						
						if(i > 0) {
							request = "success";
						} else {
							request = "error insert";
						}
					}
					
					if(type[2].equals("1")) {
						sql = "INSERT INTO ruta_tipovehiculo (idruta_rutatipovehiculo, idtipo_rutatipovehiculo) VALUES ('" + idRoute + "', '3')";
						
						prep = (PreparedStatement) connection.prepareStatement(sql);
						i = prep.executeUpdate();
						
						if(i > 0) {
							request = "success";
						} else {
							request = "error insert";
						}
					}
				} else {
					request = "error insert";
				}
				
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			request = "end";
		}
		
		return request;
	}
	
	public ArrayList<String> listRoutes() {
		ArrayList<String> alRoutes = new ArrayList<String>();
		sql = "SELECT id_ruta, lugardestino_ruta FROM rutas ORDER BY id_ruta ASC";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				alRoutes.add(res.getString("id_ruta") + "." + getNamePlace(res.getString("lugardestino_ruta")));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return alRoutes;
	}
	
	public String selectRoute(String id) {
		String request = "";
		
		sql = "SELECT * FROM rutas WHERE id_ruta = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				request += res.getString("id_ruta") + "|";
				request += res.getString("lugarorigen_ruta") + "|";
				request += getNamePlace(res.getString("lugardestino_ruta")) + "|";
				request += res.getString("duracion_ruta") + "|";
				
				if(res.getString("activo_ruta").equals("1")) {
					request += "Si" + "|";
				} else {
					request += "No" + "|";
				}
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	public String updateRoute(String id, String placeEnd, String duration, String active, String[] type) {
		String request = "", act = "";
		
		if(active.equals("Si")) {
			act = "1";
		} else {
			act = "0";
		}
		
		sql = "UPDATE rutas SET lugardestino_ruta = '" + placeEnd + "', duracion_ruta = '" + duration + "', activo_ruta = '" + act + "' WHERE id_ruta = '" + id + "'";
		
		try {
			connection = conBD.connection();
			prep = (PreparedStatement) connection.prepareStatement(sql);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				String sql1 = "SELECT count(*) FROM ruta_tipovehiculo WHERE idruta_rutatipovehiculo = '" + id + "'";
				
				PreparedStatement prep1 = (PreparedStatement) connection.prepareStatement(sql1);
				ResultSet res1 = (ResultSet) prep1.executeQuery();
				
				while(res1.next()) {
					if(Integer.parseInt(res1.getString("count(*)")) > 0) {
						String sql2 = "DELETE FROM ruta_tipovehiculo WHERE idruta_rutatipovehiculo = '" + id + "'";
						
						PreparedStatement prep2 = (PreparedStatement) connection.prepareStatement(sql2);
						int j = prep2.executeUpdate();
						
						if(j > 0) {
							request = "success";
						} else {
							request = "error update";
						}
					}
				}				
						
				if(type[0].equals("1")) {
					sql = "INSERT INTO ruta_tipovehiculo (idruta_rutatipovehiculo, idtipo_rutatipovehiculo) VALUES ('" + id + "', '1')";
					
					prep = (PreparedStatement) connection.prepareStatement(sql);
					i = prep.executeUpdate();
					
					if(i > 0) {
						request = "success";
					} else {
						request = "error update";
					}
				}
				
				if(type[1].equals("1")) {
					sql = "INSERT INTO ruta_tipovehiculo (idruta_rutatipovehiculo, idtipo_rutatipovehiculo) VALUES ('" + id + "', '2')";
					
					prep = (PreparedStatement) connection.prepareStatement(sql);
					i = prep.executeUpdate();
					
					if(i > 0) {
						request = "success";
					} else {
						request = "error update";
					}
				}
				
				if(type[2].equals("1")) {
					sql = "INSERT INTO ruta_tipovehiculo (idruta_rutatipovehiculo, idtipo_rutatipovehiculo) VALUES ('" + id + "', '3')";
					
					prep = (PreparedStatement) connection.prepareStatement(sql);
					i = prep.executeUpdate();
					
					if(i > 0) {
						request = "success";
					} else {
						request = "error update";
					}
				}
			} else {
				request = "error update";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
	
	public String deleteRoute(String id) {
		String request = "", request1 = "", request2 = "";
		String sql = "DELETE FROM ruta_tipovehiculo WHERE idruta_rutatipovehiculo = '" + id + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
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
		
		String sql1 = "DELETE FROM recorridos WHERE ruta_recorrido = '" + id + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql1);
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
		
		String sql2 = "DELETE FROM rutas WHERE id_ruta = '" + id + "'";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql2);
			int i = prep.executeUpdate();
			
			if(i > 0) {
				request2 = "success";
			} else {
				request2 = "error delete";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(request.equals("success") && request1.equals("success") && request2.equals("success")) {
			return "success";
		} else {
			return "error delete";
		}
	}
	
	public String[] getTypeVehicle(String id) {
		String[] arr = null;
		String request = "";
		String sql1 = "SELECT idtipo_rutatipovehiculo FROM ruta_tipovehiculo WHERE idruta_rutatipovehiculo = '" + id + "'";
		
		try {
			connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql1);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				request += res.getString("idtipo_rutatipovehiculo") + "|";
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		arr = request.split(Pattern.quote("|"));
		
		return arr;
	}
	
	boolean validPlaceEnd(String end) {
		boolean valid = false;
		
		sql = "SELECT count(*) FROM rutas WHERE lugardestino_ruta = '" + end + "'";
		
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
	
	String getNamePlace(String id) {
		String request = "";
		String sql = "SELECT nombre_lugar FROM lugares WHERE id_lugar = '" + id + "'";
		
		try {
			connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) { 
				request = res.getString("nombre_lugar");
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
}
