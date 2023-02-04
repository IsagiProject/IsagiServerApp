package Funciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;
import Visual.Main;

public class funcMain {
	static ArrayList<UsuarioDTO> bbddUsuarios = new ArrayList<>();

	public static boolean emailValidator(String email) {

		String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} else
			return false;
	}

	public static void actualizarDatos(ArrayList<UsuarioDTO> listaUsuarios) throws ClassNotFoundException, IOException {
		System.out.println("Actualizando...");
		System.out.println(listaUsuarios.size());
		try {
			ArrayList<UsuarioDTO> usuarios = new UsuarioDAO().listarTodos();
			bbddUsuarios.clear();
			for (UsuarioDTO usuarioDTO : usuarios) {
				bbddUsuarios.add(usuarioDTO);
			}
		} catch (Exception e) {
			System.out.println("No se ha actualizado");
			e.printStackTrace();
		}

	}

	public static void AddUsu(ArrayList<UsuarioDTO> listaUsuarios, File fichero, String nom, String ape,
			String password, String cat, String mail, boolean conectado) throws SQLException {
		UsuarioDTO user = new UsuarioDTO();
		user.setNom_usu(nom);
		user.setApe_usu(ape);
		user.setMail(mail);
		user.setPassword(password);
		user.setCategoria(cat);
		new UsuarioDAO().insertar(user);
		actualizarTabla();
	}

	public static void EliminarUsu(ArrayList<UsuarioDTO> listaUsuarios, File fichero, int usu, boolean conectado) {
		int codusu = 1000000;
		try {
			// TODO Get selected row, find user by id. Compare if the mail is the same or
			// not

			codusu = listaUsuarios.get(usu).getCod_usu();
			if (listaUsuarios.get(usu).getMail().equals(funcLogin.mail)) {
				JOptionPane.showMessageDialog(null, "No puedes borrar al ususario con el que has iniciado sesion",
						"Aviso", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (conectado) {
				new UsuarioDAO().borrar(codusu);
				actualizarTabla();
				GuardarLista(listaUsuarios, fichero);
			}
			if (usu >= 0) {
				listaUsuarios.remove(usu);
				actualizarTabla();
				GuardarLista(listaUsuarios, fichero);
			}

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Elije primero un registro de la tabla", "Aviso",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	// TODO REMOVE
	public static void GuardarLista(ArrayList<UsuarioDTO> listaUsuarios, File fichero) throws SQLException {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("FicheroUsuarios.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (UsuarioDTO a : listaUsuarios) {
				oos.writeObject(a);
			}
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void ModificarUsu(ArrayList<UsuarioDTO> listaUsuarios, File fichero, int usu, boolean conectado,
			String mail, String name, String lastname, String password, String job) throws SQLException {
		try {
			UsuarioDTO usuario = listaUsuarios.get(usu);
			try {
				UsuarioDAO dao = new UsuarioDAO();
				usuario.setNom_usu(name);
				usuario.setMail(mail);
				usuario.setApe_usu(lastname);
				usuario.setPassword(password);
				usuario.setCategoria(job);
				dao.actualizar(usuario);
				GuardarLista(listaUsuarios, fichero);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Elije primero un registro de la tabla", "Aviso",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void actualizarTabla() {
		ArrayList<UsuarioDTO> listaUsuarios = new UsuarioDAO().listarTodos();
		DefaultTableModel usuariosModel = new DefaultTableModel(
				new String[] { "Codigo", "Mail", "Nombre", "Apellido", "Contrase\u00F1a", "Categoria" },
				listaUsuarios.size());
		Main.table.setModel(usuariosModel);

		for (int i = 0; i < listaUsuarios.size(); i++) {
			usuariosModel.setValueAt(listaUsuarios.get(i).getCod_usu(), i, 0);
			usuariosModel.setValueAt(listaUsuarios.get(i).getMail(), i, 1);
			usuariosModel.setValueAt(listaUsuarios.get(i).getNom_usu(), i, 2);
			usuariosModel.setValueAt(listaUsuarios.get(i).getApe_usu(), i, 3);
			usuariosModel.setValueAt(listaUsuarios.get(i).getPassword(), i, 4);
			usuariosModel.setValueAt(listaUsuarios.get(i).getCategoria(), i, 5);
		}

	}

	public static int mostrar(int row) {
		try {
			Main.textMail.setText(Main.table.getModel().getValueAt(row, 1).toString());
			Main.textName.setText(Main.table.getModel().getValueAt(row, 2).toString());
			Main.textLastName.setText(Main.table.getModel().getValueAt(row, 3).toString());
			Main.textPassword.setText(Main.table.getModel().getValueAt(row, 4).toString());
			Main.comboBox.setSelectedItem(Main.table.getModel().getValueAt(row, 5).toString());
			Main.mailActual = Main.textMail.getText();
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Elije primero un registro de la tabla", "Aviso",
					JOptionPane.ERROR_MESSAGE);
		}
		return row;
	}

	public static void mostrar(UsuarioDTO u) {
		try {
			Main.textMail.setText(u.getMail());
			Main.textName.setText(u.getNom_usu());
			Main.textLastName.setText(u.getApe_usu());
			Main.textPassword.setText(u.getPassword());
			Main.comboBox.setSelectedItem(u.getCategoria());
			Main.mailActual = Main.textMail.getText();
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Elije primero un registro de la tabla", "Aviso",
					JOptionPane.ERROR_MESSAGE);
		}

		for (int i = 0; i < Main.table.getModel().getRowCount(); i++) {
			if (Integer.parseInt(Main.table.getModel().getValueAt(i, 0).toString()) == u.getCod_usu()) {
				Main.filadev = i;
			}
		}

	}

}
