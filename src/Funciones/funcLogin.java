package Funciones;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;

public class funcLogin {
	public static String mail = "", password = "";

	public static Statement stm;

	public static boolean cargarServer(String mail, String password) throws SQLException {
		ArrayList<UsuarioDTO> lista = new UsuarioDAO().listarTodos();
		for (UsuarioDTO usuarioDTO : lista) {
			if (usuarioDTO.getMail().equals(mail) && usuarioDTO.getPassword().equals(password)
					&& usuarioDTO.getCategoria().equals("Gerente")) {
				mail = usuarioDTO.getMail();
				password = usuarioDTO.getPassword();
				return true;
			}
		}
		return false;
	}

}
