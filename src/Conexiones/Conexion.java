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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/isagi", "root", "");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return connection;

	}

	public static void main(String[] args) {
		Connection miConexion = Conexion.getConnection();
		if (miConexion != null) {
			System.out.print("Conectado: " + miConexion);
		} else
			System.out.print("Error");
	}

	private static Conexion instancia = null;
	private static Connection con;

	private Conexion() {
		String host = "127.0.0.1";
		String user = "root";
		String pass = "";
		String dtbs = "isagi";
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Inicializar el driver
			String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al abrir la conexión.");
		}
	}

	public static Conexion getInstancia() {
		if (instancia == null)
			instancia = new Conexion();
		return instancia;
	}

	public Connection getCon() {
		return con;
	}

	public void cerrarConexion() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("Error al cerrar la conexión.");
		}
	}
}
