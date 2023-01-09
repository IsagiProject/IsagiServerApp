package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Usuarios.Camarero;
import Usuarios.Cocinero;
import Usuarios.Gerente;
import Usuarios.Usuario;
import Conexiones.Conexion;
import Conexiones.Server;
import Funciones.funcMain;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JLabel;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Container;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Rectangle;
import java.awt.Cursor;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	static int nextid;
	static int filadev;
	public static File fichero;
	private JPanel contentPane;
	JButton btnAnadir, btnMostrar;
	public static JButton btnEliminar;
	public static JButton btnModificar;
	public static JComboBox comboBox;
	public static JTextField textName;
	public static JTextField textLastName;
	public static JTextField textPassword;
	public static JTable table;
	private JButton btnSalir;
	private JButton btnBuscarPorCodigo;
	private JButton btnRegistro;
	private JButton btnLimpiar;
	public static JTextField textMail;
	public static String mailActual;

	public Main() throws ClassNotFoundException, IOException, SQLException {
		setTitle("Ventana Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Visual/cafetera.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarComponentes();
		listaUsuarios.clear();
		fichero = new File("FicheroUsuarios.obj");
		funcMain.CargarDatos(listaUsuarios, fichero, Login.conectado);
		if(Login.conectado) {
			funcMain.actualizarDatos(listaUsuarios);
		}
		funcMain.actualizarLista();

	}

	public void inicializarComponentes() {

		setBounds(100, 100, 1430, 640);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 10, 31));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setPreferredSize(new Dimension(3, 3));
		scrollPaneTabla.setOpaque(true);
		scrollPaneTabla.setForeground(new Color(0, 0, 51));
		scrollPaneTabla.setFont(new Font("Roboto", Font.PLAIN, 14));
		scrollPaneTabla.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 51)));
		scrollPaneTabla.setViewportBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 51)));
		scrollPaneTabla.setBackground(new Color(0, 0, 51));
		scrollPaneTabla.setBounds(784, 70, 611, 260);
		contentPane.add(scrollPaneTabla);

		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setShowGrid(false);
		table.setForeground(new Color(255, 112, 10));
		scrollPaneTabla.setViewportView(table);
		table.setFont(new Font("Roboto", Font.PLAIN, 12));
		table.setBackground(new Color(20, 20, 41));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);

		table.setCellSelectionEnabled(true);

		btnMostrar = new JButton("Mostrar Datos");
		btnMostrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrar.setBorder(null);
		btnMostrar.setBounds(279, 502, 198, 56);
		contentPane.add(btnMostrar);
		btnMostrar.setBackground(new Color(255, 112, 10));
		btnMostrar.setForeground(new Color(10, 10, 31));
		btnMostrar.setFont(new Font("Roboto", Font.BOLD, 19));
		btnMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnMostrar.setBackground(new Color(10, 10, 31));
				btnMostrar.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnMostrar.setBackground(new Color(255, 112, 10));
				btnMostrar.setForeground(new Color(10, 10, 31));

			}
		});
		btnBuscarPorCodigo = new JButton("Buscar Por Codigo");
		btnBuscarPorCodigo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarPorCodigo.setBorder(null);
		btnBuscarPorCodigo.setBounds(55, 502, 198, 56);
		contentPane.add(btnBuscarPorCodigo);
		btnBuscarPorCodigo.addActionListener(this);
		btnBuscarPorCodigo.setForeground(new Color(10, 10, 31));
		btnBuscarPorCodigo.setFont(new Font("Roboto", Font.BOLD, 19));
		btnBuscarPorCodigo.setBackground(new Color(255, 112, 10));
		btnBuscarPorCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBuscarPorCodigo.setBackground(new Color(10, 10, 31));
				btnBuscarPorCodigo.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBuscarPorCodigo.setBackground(new Color(255, 112, 10));
				btnBuscarPorCodigo.setForeground(new Color(10, 10, 31));

			}
		});
		btnAnadir = new JButton("Añadir");
		btnAnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnadir.setBorder(null);
		btnAnadir.setBounds(559, 145, 139, 56);
		contentPane.add(btnAnadir);
		btnAnadir.setFont(new Font("Roboto", Font.BOLD, 19));
		btnAnadir.setForeground(new Color(10, 10, 31));
		btnAnadir.setBackground(new Color(255, 112, 10));
		btnAnadir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnAnadir.setBackground(new Color(10, 10, 31));
				btnAnadir.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnAnadir.setBackground(new Color(255, 112, 10));
				btnAnadir.setForeground(new Color(10, 10, 31));

			}
		});

		btnModificar = new JButton("Modificar");
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.setBorder(null);
		btnModificar.setBounds(559, 225, 139, 56);
		contentPane.add(btnModificar);
		btnModificar.setForeground(new Color(10, 10, 31));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 19));
		btnModificar.setBackground(new Color(255, 112, 10));
		btnModificar.setEnabled(false);
		btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnModificar.setBackground(new Color(10, 10, 31));
				btnModificar.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnModificar.setBackground(new Color(255, 112, 10));
				btnModificar.setForeground(new Color(10, 10, 31));

			}
		});
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBorder(null);
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(559, 306, 139, 56);
		contentPane.add(btnEliminar);
		btnEliminar.setForeground(new Color(10, 10, 31));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 19));
		btnEliminar.setBackground(new Color(255, 112, 10));
		btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnEliminar.setBackground(new Color(10, 10, 31));
				btnEliminar.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnEliminar.setBackground(new Color(255, 112, 10));
				btnEliminar.setForeground(new Color(10, 10, 31));

			}
		});

		btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBorder(null);
		btnSalir.setBounds(1220, 539, 139, 54);
		contentPane.add(btnSalir);
		btnSalir.setForeground(new Color(10, 10, 31));
		btnSalir.setFont(new Font("Roboto", Font.BOLD, 19));
		btnSalir.addActionListener(this);
		btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSalir.setBackground(new Color(10, 10, 31));
				btnSalir.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnSalir.setBackground(new Color(235, 112, 10));
				btnSalir.setForeground(new Color(10, 10, 31));
			}
		});
		btnSalir.setBackground(new Color(255, 102, 0));

		textName = new JTextField();
		textName.setBounds(55, 154, 436, 41);
		textName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		textName.setForeground(new Color(255, 204, 102));
		textName.setFont(new Font("Arial", Font.PLAIN, 18));
		textName.setBackground(new Color(10, 10, 31));
		textName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textName.setBackground(new Color(10, 10, 41));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textName.setBackground(new Color(10, 10, 31));
			}
		});
		contentPane.add(textName);
		textName.setColumns(10);

		textLastName = new JTextField();
		textLastName.setBounds(55, 225, 436, 41);
		textLastName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		textLastName.setForeground(new Color(255, 204, 102));
		textLastName.setFont(new Font("Arial", Font.PLAIN, 18));
		textLastName.setBackground(new Color(10, 10, 31));
		textLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textLastName.setBackground(new Color(10, 10, 41));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textLastName.setBackground(new Color(10, 10, 31));
			}
		});
		contentPane.add(textLastName);
		textLastName.setColumns(10);

		textPassword = new JTextField();
		textPassword.setBounds(55, 378, 436, 41);
		textPassword.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		textPassword.setForeground(new Color(255, 204, 102));
		textPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		textPassword.setBackground(new Color(10, 10, 31));
		contentPane.add(textPassword);
		textPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textPassword.setBackground(new Color(10, 10, 41));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textPassword.setBackground(new Color(10, 10, 31));
			}
		});
		textPassword.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setBorder(null);
		comboBox.setBounds(55, 455, 181, 37);
		contentPane.add(comboBox);
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setFont(new Font("Roboto", Font.BOLD, 15));
		comboBox.setBackground(new Color(255, 102, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Camarero", "Cocinero", "Gerente" }));

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setForeground(new Color(255, 113, 10));
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 19));
		lblNewLabel.setBounds(55, 132, 99, 13);
		contentPane.add(lblNewLabel);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(255, 113, 10));
		lblApellido.setFont(new Font("Roboto", Font.BOLD, 19));
		lblApellido.setBounds(55, 201, 99, 23);
		contentPane.add(lblApellido);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(new Color(255, 113, 10));
		lblContrasea.setFont(new Font("Roboto", Font.BOLD, 19));
		lblContrasea.setBounds(55, 357, 119, 16);
		contentPane.add(lblContrasea);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/Visual/Principal (2).jpg")));
		lblNewLabel_1.setBounds(772, 0, 644, 603);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("VENTANA PRINCIPAL");
		lblNewLabel_1_1.setIcon(new ImageIcon(Main.class.getResource("/Visual/cafetera.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(255, 113, 10));
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.BOLD, 25));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 51));
		lblNewLabel_1_1.setBounds(45, 70, 298, 37);
		contentPane.add(lblNewLabel_1_1);

		btnRegistro = new JButton("Registro de Chat");
		btnRegistro.setForeground(new Color(10, 10, 31));
		btnRegistro.setFont(new Font("Roboto", Font.BOLD, 19));
		btnRegistro.setBorder(null);
		btnRegistro.addActionListener(this);
		btnRegistro.setBackground(new Color(255, 112, 10));
		btnRegistro.setBounds(501, 502, 198, 56);
		contentPane.add(btnRegistro);
		btnRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRegistro.setBackground(new Color(10, 10, 31));
				btnRegistro.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRegistro.setBackground(new Color(255, 112, 10));
				btnRegistro.setForeground(new Color(10, 10, 31));

			}
		});

		textMail = new JTextField();
		textMail.setForeground(new Color(255, 204, 102));
		textMail.setFont(new Font("Arial", Font.PLAIN, 18));
		textMail.setColumns(10);
		textMail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		textMail.setBackground(new Color(10, 10, 31));
		textMail.setBounds(55, 306, 436, 41);
		contentPane.add(textMail);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setForeground(new Color(255, 113, 10));
		lblMail.setFont(new Font("Roboto", Font.BOLD, 19));
		lblMail.setBounds(55, 273, 99, 23);
		contentPane.add(lblMail);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setForeground(new Color(10, 10, 31));
		btnLimpiar.setFont(new Font("Roboto", Font.BOLD, 19));
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(new Color(255, 112, 10));
		btnLimpiar.setBounds(559, 389, 139, 56);
		contentPane.add(btnLimpiar);
		btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLimpiar.setBackground(new Color(10, 10, 31));
				btnLimpiar.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLimpiar.setBackground(new Color(255, 112, 10));
				btnLimpiar.setForeground(new Color(10, 10, 31));

			}
		});

		btnEliminar.addActionListener(this);
		btnModificar.addActionListener(this);
		btnAnadir.addActionListener(this);
		btnMostrar.addActionListener(this);

		table.getRowSelectionAllowed();
		}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			if (btnAnadir == e.getSource()) {

				if (textMail.getText().equals("") || textName.getText().equals("") || textLastName.getText().equals("")
						|| textPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Relene los campos", "Limpieza",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				for (int i = 0; i < listaUsuarios.size(); i++) {
					if (listaUsuarios.get(i).getMail().equals(textMail.getText())) {
						textMail.setText("");
						JOptionPane.showMessageDialog(null, "Ya exisite ese correo", "Aviso",
								JOptionPane.WARNING_MESSAGE);
						textMail.grabFocus();
						return;
					}
				}
				if (!funcMain.emailValidator(textMail.getText())) {
					JOptionPane.showMessageDialog(null, "Correo no valido", "Error", JOptionPane.ERROR_MESSAGE);
					textMail.setText("");
					textMail.grabFocus();
					return;
				}

				// Crear tres if para crear tres tipos de usuarios diferentes y que llame a
				// camarero, gerente o cocinero

				if (comboBox.getSelectedItem().toString() == "Camarero") {
					funcMain.AddUsu(listaUsuarios, fichero,textName.getText() , textLastName.getText(), textPassword.getText(),"Camarero" ,textMail.getText(), Login.conectado);
					comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				} else if (comboBox.getSelectedItem().toString() == "Cocinero") {
					funcMain.AddUsu(listaUsuarios, fichero,textName.getText() , textLastName.getText(), textPassword.getText(),"Cocinero" ,textMail.getText(),  Login.conectado);
					comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				} else if (comboBox.getSelectedItem().toString() == "Gerente") {
					funcMain.AddUsu(listaUsuarios, fichero,textName.getText() , textLastName.getText(), textPassword.getText(),"Gerente" ,textMail.getText(),  Login.conectado);
					comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				}
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Se ha añadido un nuevo ");
				funcMain.actualizarLista();
				funcMain.GuardarLista(listaUsuarios, fichero);
				textName.setText("");
				textLastName.setText("");
				textPassword.setText("");
				textMail.setText("");

			}
			if (btnRegistro == e.getSource()) {
				new RegistroChat().setVisible(true);

			}
			if (btnEliminar == e.getSource()) {
				int resp = JOptionPane.showConfirmDialog(null, "Seguro que quiere borrar este Registro?", "Aviso",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resp == 0) {
					btnModificar.setEnabled(false);
					funcMain.EliminarUsu(listaUsuarios, fichero, filadev, Login.conectado);

				}
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				textName.setText("");
				textLastName.setText("");
				textPassword.setText("");
				textMail.setText("");
			}
			if (btnLimpiar == e.getSource()) {
				textName.setText("");
				textLastName.setText("");
				textMail.setText("");
				textPassword.setText("");
				JOptionPane.showMessageDialog(null, "Se han limpiado los campos", "Limpieza",
						JOptionPane.INFORMATION_MESSAGE);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
			}
			if (btnMostrar == e.getSource()) {
				listaUsuarios.clear();
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);

				filadev = funcMain.mostrar(table.getSelectedRow());
				funcMain.CargarDatos(listaUsuarios, fichero, Login.conectado);
				funcMain.actualizarLista();

			}
			if (btnModificar == e.getSource()) {
				int resp = JOptionPane.showConfirmDialog(
						null, "Seguro que quiere modificar los datos de "
								+ table.getModel().getValueAt(filadev, 2).toString() + "?",
						"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resp == 0) {
					if (textMail.getText().equals("") || textName.getText().equals("")
							|| textLastName.getText().equals("") || textPassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Relene los campos", "Limpieza",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (!funcMain.emailValidator(textMail.getText())) {
						JOptionPane.showMessageDialog(null, "Correo no valido", "Error", JOptionPane.ERROR_MESSAGE);
						textMail.setText("");
						textMail.grabFocus();
						return;
					}
					for (int i = 0; i < listaUsuarios.size(); i++) {
						if (listaUsuarios.get(i).getMail().equals(textMail.getText())
								&& !mailActual.equals(textMail.getText())) {
							JOptionPane.showMessageDialog(null, "Ya exisite ese correo", "Aviso",
									JOptionPane.WARNING_MESSAGE);
							textMail.setText("");
							textMail.grabFocus();
							return;
						}
					}

					funcMain.ModificarUsu(listaUsuarios, fichero, filadev, Login.conectado,
							textMail.getText().toString(), textName.getText().toString(),
							textLastName.getText().toString(), textPassword.getText().toString(),
							comboBox.getSelectedItem().toString());
					funcMain.actualizarLista();
					JOptionPane.showMessageDialog(null,
							"Se ha modificado a " + table.getModel().getValueAt(filadev, 2).toString() + "",
							"Modificacion", JOptionPane.INFORMATION_MESSAGE);
				}
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				textName.setText("");
				textLastName.setText("");
				textPassword.setText("");
				textMail.setText("");
			}
			if (btnSalir == e.getSource()) {
				int resp = JOptionPane.showConfirmDialog(null, "Quiere salir de la aplicacion?", "Salir",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (resp == 0) {
					JOptionPane.showMessageDialog(null, "Que tenga un buen dia", "Adios",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
			if (btnBuscarPorCodigo == e.getSource()) {
				new Buscador().setVisible(true);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}