package Funciones;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Conexiones.Conexion;
import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;
import Visual.Login;

public class funcLogin {
	public static String mail = "", password = "";

	public static Statement stm;

	public static void Conectar() throws SQLException {
		try {
			stm = Conexion.getInstancia().getCon().createStatement();
		} catch (SQLException e) {
			if (Conexion.getInstancia().getCon().createStatement() == null) {
				Login.conectado = false;
			}
		}
	}

	public static boolean cargarServer(String mail, String password) throws SQLException {
		ArrayList<UsuarioDTO> lista = new UsuarioDAO().listarTodos();
		for (UsuarioDTO usuarioDTO : lista) {
			if (usuarioDTO.getMail().equals(mail) && usuarioDTO.getPassword().equals(password)
					&& usuarioDTO.getCategoria().equals("Gerente")) {
				mail = usuarioDTO.getMail();
				password = usuarioDTO.getPassword();
				System.out.print(mail + " " + password);
				return true;
			}
		}
		return false; 
		/* 
			ResultSet rst = stm.executeQuery("select mail, password from usuarios where categoria='Gerente' and mail='"
					+ mail + "' and  password='" + password + "'");
			if (rst.next()) {
				mail = rst.getString(1);
				password = rst.getString(2);
				System.out.print(mail + " " + password);
				return true;
			}
			return false;
		 */
	}

}
