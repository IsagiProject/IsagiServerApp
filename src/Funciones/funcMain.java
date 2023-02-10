package Funciones;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;
import Visual.Main;

public class funcMain {
	public static boolean emailValidator(String email) {

		String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} else
			return false;
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
