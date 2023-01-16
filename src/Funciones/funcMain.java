package Funciones;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Conexiones.Conexion;
import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;
import Usuarios.Camarero;
import Usuarios.Cocinero;
import Usuarios.Gerente;
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
			bbddUsuarios.clear();
			PreparedStatement ps = Conexion.getInstancia().getCon().prepareStatement("select * from usuarios");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
				UsuarioDTO u = new UsuarioDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
				bbddUsuarios.add(u);
			}

			if (bbddUsuarios.size() <= listaUsuarios.size()) {
				for (UsuarioDTO a : listaUsuarios) {
					for (UsuarioDTO u : bbddUsuarios) {
						if (a.getCod_usu() == u.getCod_usu()) {
							new UsuarioDAO().actualizar(a);
							System.out.println(a.toString());
							System.out.println("Se modifico un usuario");
						} else {
							new UsuarioDAO().insertar(a);
							System.out.println("Se agrego un usuario");
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("No se ha actualizado");
			e.printStackTrace();
		}

	}

	public static void AddUsu(ArrayList<UsuarioDTO> listaUsuarios, File fichero, String nom, String ape,
			String password,
			String cat, String mail, boolean conectado) throws SQLException {
		if (conectado) {
			UsuarioDTO user = new UsuarioDTO();
			user.setNom_usu(nom);
			user.setApe_usu(ape);
			user.setMail(mail);
			user.setPassword(password);
			user.setCategoria(cat);
			ResultSet rs = (ResultSet) new UsuarioDAO().insertar(user);
			if (rs.next()) {
				if (cat.equals("Camarero")) {
					Camarero u = new Camarero(rs.getInt(1), nom, ape, mail, password);
					System.out.print("" + u.toString());
					listaUsuarios.add(u);
				} else if (cat.equals("Cocinero")) {
					Cocinero u = new Cocinero(rs.getInt(1), nom, ape, mail, password);
					System.out.print("" + u.toString());
					listaUsuarios.add(u);
				} else if (cat.equals("Gerente")) {
					Gerente u = new Gerente(rs.getInt(1), nom, ape, mail, password);
					System.out.print("" + u.toString());
					listaUsuarios.add(u);
				}
			}
		} else {
			int i;
			if (cat.equals("Camarero")) {
				Camarero u = new Camarero(listaUsuarios.get(listaUsuarios.size() - 1).getCod_usu() + 1, nom, ape, mail,
						password);
				listaUsuarios.add(u);
				System.out.print("Creado" + u.toString());
			} else if (cat.equals("Cocinero")) {
				Cocinero u = new Cocinero(listaUsuarios.get(listaUsuarios.size() - 1).getCod_usu() + 1, nom, ape, mail,
						password);
				listaUsuarios.add(u);
				System.out.print("Creado" + u.toString());
			} else if (cat.equals("Gerente")) {
				int cod;
				if (listaUsuarios.size() <= 0) {
					cod = 1;
				} else {
					cod = listaUsuarios.get(listaUsuarios.size() - 1).getCod_usu() + 1;
				}
				Gerente u = new Gerente(cod, nom, ape, mail, password);
				listaUsuarios.add(u);
				System.out.print("Creado" + u.toString());
			}
		}

	}

	public static void EliminarUsu(ArrayList<UsuarioDTO> listaUsuarios, File fichero, int usu, boolean conectado) {
		int codusu = 1000000;
		try {

			codusu = listaUsuarios.get(usu).getCod_usu();
			if (listaUsuarios.get(usu).getMail().equals(funcLogin.mail)) {
				JOptionPane.showMessageDialog(null, "No puedes borrar al ususario con el que has iniciado sesion",
						"Aviso", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (conectado) {
				new UsuarioDAO().borrar(codusu);
				actualizarLista();
				GuardarLista(listaUsuarios, fichero);
			}
			if (usu >= 0) {
				listaUsuarios.remove(usu);
				actualizarLista();
				GuardarLista(listaUsuarios, fichero);
			}

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Elije primero un registro de la tabla", "Aviso",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void CargarDatos(ArrayList<UsuarioDTO> listaUsuarios, File fichero, boolean conectado)
			throws IOException, ClassNotFoundException, SQLException {
		try {
			if (fichero.exists()) {
				FileInputStream fie = new FileInputStream(fichero);
				ObjectInputStream ois = new ObjectInputStream(fie);

				UsuarioDTO a;
				while (true) {
					a = (UsuarioDTO) ois.readObject();
					listaUsuarios.add(a);
					System.out.println(a.toString() + " " + a.getCategoria());
				}
			} else
				System.out.print("Fichero no existe");

		} catch (EOFException e) {
			System.out.println("\n****** FIN DE FICHERO *******");
		} catch (NullPointerException e) {
			System.out.println("\n****** FIN DE FICHERO *******");
		} catch (FileNotFoundException e) {
			System.out.print("File not exists");
		} catch (IOException e) {
			System.out.print("a");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.print("b");
			e.printStackTrace();
		}
	}

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
			if (conectado) {
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

			} else {

				if (usu >= 0) {

					usuario.setNom_usu(name);
					usuario.setMail(mail);
					usuario.setApe_usu(lastname);
					usuario.setPassword(password);
					usuario.setCategoria(job);
					System.out.println(usuario.toString());
					GuardarLista(listaUsuarios, fichero);

				}
			}
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Elije primero un registro de la tabla", "Aviso",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void actualizarLista() {

		DefaultTableModel usuarios = new DefaultTableModel(
				new String[] { "Codigo", "Mail", "Nombre", "Apellido", "Contrase\u00F1a", "Categoria" },
				Main.listaUsuarios.size());
		Main.table.setModel(usuarios);
		TableModel datos = Main.table.getModel();
		for (int i = 0; i < Main.listaUsuarios.size(); i++) {
			UsuarioDTO u = (UsuarioDTO) (Main.listaUsuarios.get(i));
			usuarios.setValueAt(u.getCod_usu(), i, 0);
			usuarios.setValueAt(u.getMail(), i, 1);
			usuarios.setValueAt(u.getNom_usu(), i, 2);
			usuarios.setValueAt(u.getApe_usu(), i, 3);
			usuarios.setValueAt(u.getPassword(), i, 4);
			usuarios.setValueAt(u.getCategoria(), i, 5);
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

}
