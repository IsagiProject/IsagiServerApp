package Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Funciones.funcLogin;
import Funciones.funcMain;
import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.UsuarioDTO;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {
	// public static ArrayList<UsuarioDTO> old___listaUsuarios = new ArrayList<>();
	static UsuarioDAO usuarioDAO = new UsuarioDAO();
	static int nextid;
	public static int filadev;
	static String selectedMail;
	static int selectedId;
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
	private JButton btFichajes;

	public Main() throws ClassNotFoundException, IOException, SQLException {
		setTitle("Ventana Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Visual/cafetera.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarComponentes();
		fichero = new File("FicheroUsuarios.obj");

		funcMain.actualizarTabla();
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
		btnAnadir.setBounds(560, 164, 139, 56);
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
		btnModificar.setBounds(560, 244, 139, 56);
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
		btnEliminar.setBounds(560, 325, 139, 56);
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
		btnLimpiar.setBounds(560, 408, 139, 56);
		contentPane.add(btnLimpiar);
		
		btFichajes = new JButton("Fichajes");
		btFichajes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new Fichar().setVisible(true);
		    }
		});
		btFichajes.setForeground(new Color(10, 10, 31));
		btFichajes.setFont(new Font("Roboto", Font.BOLD, 19));
        btFichajes.setBorder(null);
        btFichajes.setBackground(new Color(255, 112, 10));
        btFichajes.setBounds(559, 83, 139, 56);
        contentPane.add(btFichajes);
        btFichajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btFichajes.setBackground(new Color(10, 10, 31));
                btFichajes.setForeground(new Color(255, 112, 10));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btFichajes.setBackground(new Color(255, 112, 10));
                btFichajes.setForeground(new Color(10, 10, 31));

            }
        });
		
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

				if (!funcMain.emailValidator(textMail.getText())) {
					JOptionPane.showMessageDialog(null, "Correo no valido", "Error", JOptionPane.ERROR_MESSAGE);
					textMail.setText("");
					textMail.grabFocus();
					return;
				}

				if (usuarioDAO.existeMail(textMail.getText())) {
					JOptionPane.showMessageDialog(null, "El correo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				UsuarioDTO u = new UsuarioDTO(0, textName.getText(), textLastName.getText(), textMail.getText(),
						textPassword.getText(), comboBox.getSelectedItem().toString());
				usuarioDAO.insertar(u);
				comboBox.setSelectedIndex(comboBox.getSelectedIndex());
				funcMain.actualizarTabla();

				JOptionPane.showMessageDialog(null, "Se ha añadido un nuevo");
				resetModifyDelete();
			}

			if (btnRegistro == e.getSource()) {
				new RegistroChat().setVisible(true);
			}

			if (btnEliminar == e.getSource()) {
				handleDetele();
			}

			if (btnLimpiar == e.getSource()) {
				handleClear();
			}

			if (btnMostrar == e.getSource()) {
				handleShow();

			}
			if (btnModificar == e.getSource()) {
				int resp = JOptionPane.showConfirmDialog(
						null, "Seguro que quiere modificar los datos de " + selectedMail + "?",
						"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (resp == 1) {
					resetModifyDelete();
					return;
				}

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

				if (Main.selectedMail.equals(funcLogin.mail) && !textMail.getText().equals(Main.selectedMail)) {
					JOptionPane.showMessageDialog(null, "No puede cambiar su correo", "Error",
							JOptionPane.ERROR_MESSAGE);
					textMail.setText(funcLogin.mail);
					return;
				}

				// Si cambio el mail seleccionado
				if (!textMail.getText().equals(Main.selectedMail) && usuarioDAO.existeMail(textMail.getText())) {
					JOptionPane.showMessageDialog(null, "El correo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int codigo = (int) table.getValueAt(filadev, 0);
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCod_usu(codigo);
				usuarioDTO.setNom_usu(textName.getText());
				usuarioDTO.setApe_usu(textLastName.getText());
				usuarioDTO.setMail(textMail.getText());
				usuarioDTO.setPassword(textPassword.getText());
				usuarioDTO.setCategoria(comboBox.getSelectedItem().toString());

				if (usuarioDAO.actualizar(usuarioDTO)) {
					JOptionPane.showMessageDialog(null,
							"Se ha modificado a " + table.getModel().getValueAt(filadev, 2).toString() + "",
							"Modificacion", JOptionPane.INFORMATION_MESSAGE);
				}
				funcMain.actualizarTabla();
				resetModifyDelete();
			}
			if (btnSalir == e.getSource()) {
				handleExit();
			}
			if (btnBuscarPorCodigo == e.getSource()) {
				new Buscador().setVisible(true);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	private void handleDetele() {
		int resp = JOptionPane.showConfirmDialog(null, "Seguro que quiere borrar este Registro?", "Aviso",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (resp == 0) {
			btnModificar.setEnabled(false);
			usuarioDAO.borrar(selectedId);
		}
		resetModifyDelete();
	}

	private void handleExit() {
		int resp = JOptionPane.showConfirmDialog(null, "Quiere salir de la aplicacion?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (resp == 0) {
			JOptionPane.showMessageDialog(null, "Que tenga un buen dia", "Adios",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	private void resetModifyDelete() {
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		textName.setText("");
		textLastName.setText("");
		textPassword.setText("");
		textMail.setText("");
	}

	private void handleShow() {
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		selectedMail = table.getValueAt(table.getSelectedRow(), 1).toString();
		selectedId = (int) table.getValueAt(table.getSelectedRow(), 0);

		filadev = funcMain.mostrar(table.getSelectedRow());
		funcMain.actualizarTabla();
	}

	private void handleClear() {
		textName.setText("");
		textLastName.setText("");
		textMail.setText("");
		textPassword.setText("");
		JOptionPane.showMessageDialog(null, "Se han limpiado los campos", "Limpieza",
				JOptionPane.INFORMATION_MESSAGE);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
}
