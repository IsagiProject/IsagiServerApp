package Funciones;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;

public class funcLogin {
	public static String mail = "", password = "", categoria = "";
	public static int idUsuario;

	public static Statement stm;

	public static boolean cargarServer(String mail, String password) throws SQLException {
		ArrayList<UsuarioDTO> lista = new UsuarioDAO().listarTodos();
		for (UsuarioDTO usuarioDTO : lista) {
			if (usuarioDTO.getMail().equals(mail) && usuarioDTO.getPassword().equals(password)) {
				mail = usuarioDTO.getMail();
				password = usuarioDTO.getPassword();
				idUsuario = usuarioDTO.getCod_usu();
				if (usuarioDTO.getCategoria().equals("Gerente")) {
					categoria = "Gerente";
				} else {
					categoria = "Usuario";
				}
				return true;
			}
		}
		return false;
	}

}
