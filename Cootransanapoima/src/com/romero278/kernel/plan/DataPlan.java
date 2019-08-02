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
	
	public ArrayList<Double> getTotalToursInAWeek() {
		ArrayList<Double> result = new ArrayList<>();
		
		String sql = "SELECT count(*) FROM gestor_rutas.recorridos t1 INNER JOIN gestor_rutas.rutas t2 ON t1.ruta_recorrido = t2.id_ruta where t2.activo_ruta = '1' group by t1.ruta_recorrido";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				result.add(Double.parseDouble(res.getString("count(*)")));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Double> getTotalToursPerDay(String d) {
		ArrayList<Double> result = new ArrayList<>();
		
		String sql = "SELECT count(*) FROM gestor_rutas.recorridos t1 INNER JOIN gestor_rutas.rutas t2 ON t1.ruta_recorrido = t2.id_ruta where t2.activo_ruta = '1' and t1.dia_recorrido = '" + d + "' group by t1.ruta_recorrido";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				result.add(Double.parseDouble(res.getString("count(*)")));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Integer> getNumberMobilesPerTour() {
		ArrayList<Integer> current = new ArrayList<>();
		int count = 0;
		
		String sql = "SELECT ruta_recorrido FROM gestor_rutas.recorridos t1 INNER JOIN gestor_rutas.rutas t2 ON t1.ruta_recorrido = t2.id_ruta where t2.activo_ruta = '1' group by t1.ruta_recorrido";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				String sql1 = "SELECT idtipo_rutatipovehiculo FROM gestor_rutas.ruta_tipovehiculo where idruta_rutatipovehiculo = '" + res.getString("ruta_recorrido") + "'";
				
				Connection connection1 = conBD.connection();
				PreparedStatement prep1 = (PreparedStatement) connection1.prepareStatement(sql1);
				ResultSet res1 = (ResultSet) prep1.executeQuery();
				
				while(res1.next()) {
					String sql2 = "SELECT count(*) FROM moviles where tipo_movil = '" + res1.getString("idtipo_rutatipovehiculo") + "' and activo_movil = '1'";
					
					Connection connection2 = conBD.connection();
					PreparedStatement prep2 = (PreparedStatement) connection2.prepareStatement(sql2);
					ResultSet res2 = (ResultSet) prep2.executeQuery();
					
					while(res2.next()) {
						count += Integer.parseInt(res2.getString("count(*)"));
					}
					
					connection2.close();
				}
				
				current.add(count);
				count = 0;
				
				connection1.close();
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return current;
	}
	
	public ArrayList<Integer> getNumberMobilesPerTourPerDay(String d) {
		ArrayList<Integer> current = new ArrayList<>();
		int count = 0;
		
		String sql = "SELECT ruta_recorrido FROM gestor_rutas.recorridos t1 INNER JOIN gestor_rutas.rutas t2 ON t1.ruta_recorrido = t2.id_ruta where t2.activo_ruta = '1' and t1.dia_recorrido = '" + d + "' group by t1.ruta_recorrido";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				String sql1 = "SELECT idtipo_rutatipovehiculo FROM gestor_rutas.ruta_tipovehiculo where idruta_rutatipovehiculo = '" + res.getString("ruta_recorrido") + "'";
				
				Connection connection1 = conBD.connection();
				PreparedStatement prep1 = (PreparedStatement) connection1.prepareStatement(sql1);
				ResultSet res1 = (ResultSet) prep1.executeQuery();
				
				while(res1.next()) {
					String sql2 = "SELECT count(*) FROM moviles where tipo_movil = '" + res1.getString("idtipo_rutatipovehiculo") + "' and activo_movil = '1'";
					
					Connection connection2 = conBD.connection();
					PreparedStatement prep2 = (PreparedStatement) connection2.prepareStatement(sql2);
					ResultSet res2 = (ResultSet) prep2.executeQuery();
					
					while(res2.next()) {
						count += Integer.parseInt(res2.getString("count(*)"));
					}
					
					connection2.close();
				}
				
				current.add(count);
				count = 0;
				
				connection1.close();
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return current;
	}
	
	public ArrayList<String> getActiveRoutes() {
		ArrayList<String> current = new ArrayList<>();
		
		String sql = "SELECT id_ruta from rutas WHERE activo_ruta = '1' ORDER BY id_ruta ASC";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				current.add(res.getString("id_ruta"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return current;
	}
	
	public ArrayList<String> getActiveRoutesPerDay(String d) {
		ArrayList<String> current = new ArrayList<>();
		
		String sql = "SELECT id_ruta FROM gestor_rutas.rutas t1 INNER JOIN gestor_rutas.recorridos t2 ON t2.ruta_recorrido = t1.id_ruta where t1.activo_ruta = '1' and t2.dia_recorrido = '" + d + "' group by t2.ruta_recorrido";
		
		try {
			Connection connection = conBD.connection();
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet res = (ResultSet) prep.executeQuery();
			
			while(res.next()) {
				current.add(res.getString("id_ruta"));
			}
			
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return current;
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
