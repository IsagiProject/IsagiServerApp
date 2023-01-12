package Funciones;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Conexiones.Conexion;
import ModeloBD_DTO.UsuarioDTO;
import Usuarios.Camarero;
import Visual.Login;
import Visual.Main;

public class funcLogin {
	public static String mail = "", password = "";
	
	public static Statement stm;

	public static void Conectar() throws SQLException {
		try {
		
			stm = Conexion.getConnection().createStatement();
		} catch (SQLException e) {
			if(Conexion.getConnection().createStatement() == null){
			Login.conectado=false;
			
		}}
	}

	public static boolean cargarServer(String mail, String password) throws SQLException {
		ResultSet rst = stm.executeQuery("select mail, password from usuarios where categoria='Gerente' and mail='"
				+ mail + "' and  password='" + password + "'");
		if (rst.next()) {
			mail = rst.getString(1);
			password = rst.getString(2);
			System.out.print(mail + " " + password);
			return true;
		}
		return false;
	}

	
}
