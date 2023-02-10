package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Conexiones.Cliente;

public class Chat extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;
	private JButton cancelButton;
	Cliente cliente;
	ActionEvent e;
	private JPanel buttonPane;
	private JLabel lblNewLabel;
	private JLabel myMessage;
	public JTextPane textPane;
	public JScrollPane scrollPane;

	public Chat() {
		setTitle("Chat\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Chat.class.getResource("/Visual/cafetera.png")));
		
		inicializarComponentes();
		cliente = new Cliente(this);
		cliente.sendMessage(JOptionPane.showInputDialog(null, "Usuario", "Usuario", 1));
	}

	private void inicializarComponentes() {
		setBounds(100, 100, 373, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(10, 10, 31));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 419, 339, 29);
		contentPanel.add(textField);
		textField.setColumns(10);
		contentPanel.setBackground(new Color(10, 10, 31));

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(new Color(0, 0, 51));
		scrollPane.setBounds(24, 10, 310, 389);
		contentPanel.add(scrollPane);

		textPane = new JTextPane();
		textPane.setFont(new Font("Roboto", Font.BOLD, 11));
		textPane.setBackground(new Color(0, 0, 51));
		textPane.setForeground(new Color(255, 102, 0));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		buttonPane = new JPanel();
		buttonPane.setBackground(new Color(10, 10, 31));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Enviar");
		okButton.setActionCommand("OK");
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
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cerrar");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setForeground(new Color(0, 0, 51));
		cancelButton.setFont(new Font("Roboto", Font.BOLD, 15));
		cancelButton.setBackground(new Color(255, 102, 0));
		cancelButton.addActionListener(this);
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
		buttonPane.add(cancelButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (okButton == e.getSource()) {
			cliente.sendMessage(textField.getText());
			textPane.setText(textPane.getText()+"\n\n"+textField.getText());
			textField.setText("");
		}
		if (cancelButton == e.getSource()) {
			cliente.sendMessage(cliente.END_WORD);
			dispose();
		}
	}
}
