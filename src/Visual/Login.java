package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexiones.Conexion;
import Conexiones.Server;
import Funciones.funcLogin;
import Usuarios.Usuario;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame implements ActionListener {
	public static boolean conectado = false;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnEntrar, btnRegistrar;

	private JLabel lblImage;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		

		int resp = JOptionPane.showConfirmDialog(null, "Quiere conectarse al servidor?", "Conexion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (resp == 0) {
			conectado = true;
			System.out.print("conectado");
			funcLogin.Conectar();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					// new Server().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Login() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Visual/cafetera.png")));
		setResizable(false);
		setForeground(new Color(51, 51, 51));
		setBackground(SystemColor.controlDkShadow);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		setTitle("Inicio de Sesion");

		inicializarComponentes();
	}

	public void inicializarComponentes() {
		setBounds(100, 100, 888, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 10, 31));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setBackground(new Color(10, 10, 41));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textField.setBackground(new Color(10, 10, 31));
			}
		});
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		textField.setForeground(new Color(255, 204, 102));
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setBackground(new Color(10, 10, 31));
		textField.setBounds(69, 273, 436, 45);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 204, 102));
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
		passwordField.setBackground(new Color(10, 10, 31));
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setBackground(new Color(10, 10, 41));
			}

			@Override
			public void focusLost(FocusEvent e) {
				passwordField.setBackground(new Color(10, 10, 31));
			}
		});
		passwordField.setBounds(69, 366, 436, 45);

		contentPane.add(passwordField);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnEntrar.setBackground(new Color(10, 10, 31));
				btnEntrar.setForeground(new Color(255, 102, 0));
				btnEntrar.setBounds(260, 461, 200, 77);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnEntrar.setBackground(new Color(235, 112, 10));
				btnEntrar.setForeground(new Color(10, 10, 31));
				btnEntrar.setBounds(270, 471, 180, 57);
			}
		});
		btnEntrar.setBorder(new EmptyBorder(0, 0, 1, 1));
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setForeground(new Color(10, 10, 31));
		btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnEntrar.setBackground(new Color(235, 112, 10));
		btnEntrar.setBounds(270, 471, 180, 57);
		btnEntrar.addActionListener(this);
		contentPane.add(btnEntrar);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setSize(new Dimension(1, 1));
		btnRegistrar.setBorder(new EmptyBorder(2, 2, 2, 2));
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.setForeground(new Color(10, 10, 31));
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnRegistrar.setBackground(new Color(255, 112, 10));
		btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRegistrar.setBackground(new Color(10, 10, 31));
				btnRegistrar.setForeground(new Color(235, 112, 10));
				btnRegistrar.setBounds(59, 460, 198, 78);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRegistrar.setBackground(new Color(235, 112, 10));
				btnRegistrar.setForeground(new Color(10, 10, 31));
				btnRegistrar.setBounds(69, 470, 178, 58);
			}
		});
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(69, 470, 178, 58);
		contentPane.add(btnRegistrar);

		JLabel lblNewLabel = new JLabel("Mail");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 19));
		lblNewLabel.setForeground(new Color(235, 112, 10));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(69, 252, 81, 13);
		contentPane.add(lblNewLabel);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(new Color(235, 112, 10));
		lblContrasea.setFont(new Font("Roboto", Font.BOLD, 19));
		lblContrasea.setBackground(new Color(51, 51, 51));
		lblContrasea.setBounds(69, 340, 151, 13);
		contentPane.add(lblContrasea);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(69, 167, 1, 1);
		contentPane.add(layeredPane);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/Visual/logo.png")));
		lblNewLabel_3.setBounds(156, 0, 212, 187);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Visual/cafe.jpg")));
		lblNewLabel_1.setBounds(554, 0, 320, 589);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("INICIO DE SESI\u00D3N");
		lblNewLabel_2.setBackground(new Color(25, 25, 45));
		lblNewLabel_2.setForeground(new Color(235, 112, 10));
		lblNewLabel_2.setFont(new Font("Roboto Black", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(157, 145, 425, 90);
		contentPane.add(lblNewLabel_2);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e){
		if (btnRegistrar == e.getSource()) {
			Registrarse r;
			try {
				r = new Registrarse();
				r.setVisible(true);
				dispose();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if (conectado) {
			if (btnEntrar == e.getSource()) {
				Main miMain;
				try {
					miMain = new Main();

					if (funcLogin.cargarServer(textField.getText(), passwordField.getText()) == true) {
						JOptionPane.showMessageDialog(null, "Datos correctos, bienvenido", "Bienvenido",
								JOptionPane.INFORMATION_MESSAGE);
						miMain.setVisible(true);
						dispose();
					} else
						JOptionPane.showMessageDialog(null, "Usuario no valido", "Error", JOptionPane.WARNING_MESSAGE);

					textField.setText("");
					passwordField.setText("");
					textField.grabFocus();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else {
			if (btnEntrar == e.getSource()) {
				Main miMain;
				try {
					miMain = new Main();
					for (Usuario u : miMain.listaUsuarios) {
						if (u.getMail().equals(textField.getText()) && u.getPassword().equals(passwordField.getText())
								&& u.getCategoria().equals("Gerente")) {
							miMain.mailActual = u.getMail();
							miMain.setVisible(true);
							dispose();
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "Usuario no valido", "Error", JOptionPane.WARNING_MESSAGE);
					textField.setText("");
					passwordField.setText("");
					textField.grabFocus();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
}
