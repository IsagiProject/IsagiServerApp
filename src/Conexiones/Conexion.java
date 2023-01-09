package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;



public class Conexion {
public static Connection connection;
	
	@SuppressWarnings("deprecation")
	public static Connection getConnection() {
		System.out.println("CONECTADO");
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			   connection=DriverManager.getConnection("jdbc:mysql://localhost/isagi","root" ,"");
			 
			 }catch(Exception ex) {
				 ex.printStackTrace();
			 }
		return connection;
			
	}
	public static void main(String[] args) {
		Connection miConexion = Conexion.getConnection();
		if(miConexion!=null) {
			System.out.print("Conectado: "+miConexion);
		}else 			System.out.print("Error");
	}
}