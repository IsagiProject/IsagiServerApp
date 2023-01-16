package Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Funciones.funcMain;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Registrarse extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textLastName;
	private JButton btnAceptar, btnSalir;
	static Main miMain;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JTextField textPassword;
	private JTextField textMail;
	private JLabel lblMail;

	public Registrarse() throws ClassNotFoundException, IOException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registrarse.class.getResource("/Visual/cafetera.png")));
		setTitle("Registrarse");
		inicializarComponentes();

		miMain = new Main();
	}

	public void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 10, 31));
		contentPane.setForeground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textName = new JTextField();
		textName.setBounds(55, 153, 436, 45);
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
		textLastName.setBounds(55, 229, 436, 45);
		;
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

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBorder(null);
		btnAceptar.setForeground(new Color(0, 0, 51));
		btnAceptar.setFont(new Font("Roboto", Font.BOLD, 19));
		btnAceptar.setBackground(new Color(255, 112, 10));
		btnAceptar.addActionListener(this);
		btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnAceptar.setBackground(new Color(10, 10, 31));
				btnAceptar.setForeground(new Color(255, 112, 10));
				btnAceptar.setBounds(260, 461, 200, 77);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnAceptar.setBackground(new Color(235, 112, 10));
				btnAceptar.setForeground(new Color(10, 10, 31));
				btnAceptar.setBounds(270, 471, 180, 57);
			}
		});
		btnAceptar.setBounds(270, 471, 180, 57);
		contentPane.add(btnAceptar);

		btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(0, 0, 51));
		btnSalir.setBorder(null);
		btnSalir.setFont(new Font("Roboto Black", Font.BOLD, 19));
		btnSalir.setBackground(new Color(255, 112, 10));
		btnSalir.addActionListener(this);
		btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSalir.setBackground(new Color(10, 10, 31));
				btnSalir.setForeground(new Color(255, 112, 10));
				btnSalir.setBounds(59, 460, 198, 78);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnSalir.setBackground(new Color(235, 112, 10));
				btnSalir.setForeground(new Color(10, 10, 31));
				btnSalir.setBounds(69, 470, 178, 58);
			}
		});
		btnSalir.setBounds(69, 470, 178, 58);
		contentPane.add(btnSalir);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 19));
		lblNewLabel.setForeground(new Color(255, 113, 10));
		lblNewLabel.setBounds(55, 130, 99, 13);
		contentPane.add(lblNewLabel);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(255, 113, 10));
		lblApellido.setFont(new Font("Roboto", Font.BOLD, 19));
		lblApellido.setBounds(55, 208, 99, 23);
		contentPane.add(lblApellido);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(new Color(255, 113, 10));
		lblContrasea.setFont(new Font("Roboto", Font.BOLD, 19));
		lblContrasea.setBounds(55, 364, 119, 16);
		contentPane.add(lblContrasea);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Registrarse.class.getResource("/Visual/registrar (4).jpg")));
		lblNewLabel_2.setBounds(553, 0, 321, 591);
		contentPane.add(lblNewLabel_2);

		textPassword = new JTextField();
		textPassword.setBounds(55, 388, 436, 45);
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

		lblNewLabel_1 = new JLabel("REGISTRARSE");
		lblNewLabel_1.setBounds(75, 72, 191, 29);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 113, 10));
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 25));
		lblNewLabel_1.setBackground(new Color(0, 0, 51));

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Registrarse.class.getResource("/Visual/cafetera.png")));
		lblNewLabel_3.setBounds(55, 62, 57, 49);
		contentPane.add(lblNewLabel_3);

		textMail = new JTextField();
		textMail.setForeground(new Color(255, 204, 102));
		textMail.setFont(new Font("Arial", Font.PLAIN, 18));
		textMail.setColumns(10);
		textMail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(235, 112, 10)));
		textMail.setBackground(new Color(10, 10, 31));
		textMail.setBounds(55, 309, 436, 45);
		contentPane.add(textMail);

		lblMail = new JLabel("Mail");
		lblMail.setForeground(new Color(255, 113, 10));
		lblMail.setFont(new Font("Roboto", Font.BOLD, 19));
		lblMail.setBounds(55, 284, 99, 23);
		contentPane.add(lblMail);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (btnAceptar == e.getSource()) {
			if (textMail.getText().equals("") || textName.getText().equals("") || textLastName.getText().equals("")
					|| textPassword.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Relene los campos", "Limpieza", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!funcMain.emailValidator(textMail.getText())) {
				JOptionPane.showMessageDialog(null, "Correo no valido", "Error", JOptionPane.ERROR_MESSAGE);
				textMail.setText("");
				return;
			}
			for (int i = 0; i < miMain.listaUsuarios.size(); i++) {
				if (miMain.listaUsuarios.get(i).getMail().equals(textMail.getText())) {
					JOptionPane.showMessageDialog(null, "Ya exisite ese correo", "Aviso", JOptionPane.WARNING_MESSAGE);
					textMail.setText("");
					textMail.grabFocus();
					return;
				}
			}
			try {
				funcMain.AddUsu(miMain.listaUsuarios, miMain.fichero, textName.getText(), textLastName.getText(),
						textPassword.getText(), "Gerente", textMail.getText(), Login.conectado);
				funcMain.GuardarLista(miMain.listaUsuarios, miMain.fichero);
				miMain.dispose();
				JOptionPane.showMessageDialog(null, "Ya se ha registrado. Gracias");

				Login miLogin = new Login();
				miLogin.setVisible(true);
				dispose();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

		if (btnSalir == e.getSource()) {
			try {
				Login miLogin = new Login();
				miLogin.setVisible(true);
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

}
