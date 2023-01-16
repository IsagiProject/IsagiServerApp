package Visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Funciones.funcMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Buscador extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textField;

	public Buscador() {
		setBackground(new Color(10, 10, 31));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setTitle("Buscador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscador.class.getResource("/Visual/cafetera.png")));
		getContentPane().setBackground(new Color(10, 10, 31));
		setBounds(100, 100, 406, 231);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(10, 10, 31));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

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
		textField.setBounds(29, 85, 332, 45);
		contentPanel.add(textField);
		textField.setColumns(10);

		cancelButton = new JButton("Cancelar");
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setBorder(null);
		cancelButton.setBounds(293, 158, 89, 25);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(this);
		cancelButton.setForeground(new Color(0, 0, 51));
		cancelButton.setFont(new Font("Roboto", Font.BOLD, 15));
		cancelButton.setBackground(new Color(255, 102, 0));
		cancelButton.setActionCommand("Cancel");
		cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				cancelButton.setBackground(new Color(10, 10, 31));
				cancelButton.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				cancelButton.setBackground(new Color(235, 112, 10));
				cancelButton.setForeground(new Color(10, 10, 31));
			}
		});
		cancelButton.setBackground(new Color(255, 102, 0));

		okButton = new JButton("Buscar");
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setBounds(188, 158, 89, 25);
		contentPanel.add(okButton);
		okButton.setBorder(null);
		okButton.addActionListener(this);
		okButton.setForeground(new Color(0, 0, 51));
		okButton.setFont(new Font("Roboto", Font.BOLD, 15));
		okButton.setBackground(new Color(255, 102, 0));
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		okButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				okButton.setBackground(new Color(10, 10, 31));
				okButton.setForeground(new Color(255, 112, 10));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				okButton.setBackground(new Color(235, 112, 10));
				okButton.setForeground(new Color(10, 10, 31));
			}
		});
		okButton.setBackground(new Color(255, 102, 0));

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setForeground(new Color(255, 113, 10));
		lblCodigo.setFont(new Font("Roboto", Font.BOLD, 19));
		lblCodigo.setBounds(29, 61, 99, 25);
		contentPanel.add(lblCodigo);

		JLabel lblNewLabel_1_1 = new JLabel("BUSCADOR");
		lblNewLabel_1_1.setIcon(new ImageIcon(Buscador.class.getResource("/Visual/cafetera.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(255, 113, 10));
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.BOLD, 25));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 51));
		lblNewLabel_1_1.setBounds(29, 14, 179, 37);
		contentPanel.add(lblNewLabel_1_1);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(10, 10, 31));
			buttonPane.setForeground(new Color(0, 0, 0));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (cancelButton == e.getSource()) {
			int resp = JOptionPane.showConfirmDialog(null, "Quieres cancelar la busqueda?", "Cancelar",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resp == 0) {
				dispose();
			}
		}
		if (okButton == e.getSource()) {
			boolean found = false;
			try {
				for (int pos = 0; pos < Main.listaUsuarios.size(); pos++) {
					if (Main.listaUsuarios.get(pos).getCod_usu() == Integer.parseInt(textField.getText())) {
						found = true;

						Main.filadev = funcMain.mostrar(pos);

						Main.btnEliminar.setEnabled(true);
						Main.btnModificar.setEnabled(true);
						dispose();
					}

				}
				if (found == false) {
					JOptionPane.showMessageDialog(null, "no se encontron ningun articulo asi", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Mete un Codigo", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
