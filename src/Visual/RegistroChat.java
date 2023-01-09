package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Conexiones.Server;

import Usuarios.Usuario;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class RegistroChat extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton volverButton;
	private JTable table;
	private JComboBox comboBox;
	String[] usus;

	public RegistroChat() {
		setTitle("Registro del Chat");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroChat.class.getResource("/Visual/cafetera.png")));

		inicializarComponentes();
		actualizarLista();
		comboBox.setSelectedIndex(0);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegistroChat.class.getResource("/Visual/Registro.jpg")));
		lblNewLabel.setBounds(489, 0, 583, 506);
		contentPanel.setForeground(new Color(0, 0, 51));
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("REGISTRO DEL CHAT");
		lblNewLabel_1_1.setIcon(new ImageIcon(RegistroChat.class.getResource("/Visual/cafetera.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(255, 113, 10));
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.BOLD, 25));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 51));
		lblNewLabel_1_1.setBounds(60, 67, 298, 37);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblMailDelUsuario = new JLabel("Mail del Usuario");
		lblMailDelUsuario.setForeground(new Color(255, 113, 10));
		lblMailDelUsuario.setFont(new Font("Roboto", Font.BOLD, 19));
		lblMailDelUsuario.setBounds(80, 152, 209, 17);
		contentPanel.add(lblMailDelUsuario);

	}

	private void inicializarComponentes() {
		setBounds(100, 100, 1086, 543);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(10, 10, 31));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			volverButton = new JButton("Volver");
			volverButton.setFont(new Font("Roboto", Font.BOLD, 19));
			volverButton.setBounds(80, 340, 264, 55);
			contentPanel.add(volverButton);
			volverButton.setForeground(new Color(0, 0, 51));
			volverButton.setBorder(null);
			volverButton.addActionListener(this);
			volverButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					volverButton.setBackground(new Color(10, 10, 31));
					volverButton.setForeground(new Color(255, 112, 10));
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					volverButton.setBackground(new Color(255, 112, 10));
					volverButton.setForeground(new Color(10, 10, 31));

				}
			});
			volverButton.setBackground(new Color(255, 112, 10));
			volverButton.setActionCommand("Cancel");
		}

		JScrollPane scrollPaneTabla = new JScrollPane();
		scrollPaneTabla.setViewportBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 51)));
		scrollPaneTabla.setPreferredSize(new Dimension(3, 3));
		scrollPaneTabla.setOpaque(true);
		scrollPaneTabla.setForeground(new Color(0, 0, 51));
		scrollPaneTabla.setFont(new Font("Roboto", Font.PLAIN, 14));
		scrollPaneTabla.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 51)));
		scrollPaneTabla.setBackground(new Color(0, 0, 51));
		scrollPaneTabla.setBounds(532, 67, 515, 260);
		contentPanel.add(scrollPaneTabla);

		table = new JTable();
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(new Color(255, 112, 10));
		table.setFont(new Font("Roboto", Font.PLAIN, 12));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBackground(new Color(20, 20, 41));
		scrollPaneTabla.setViewportView(table);

		comboBox = new JComboBox();
		usus = new String[Main.listaUsuarios.size() + 1];
		usus[0] = "Todos";
		for (int i = 0; i < Main.listaUsuarios.size(); i++) {
			usus[i + 1] = Main.listaUsuarios.get(i).getMail();
		}
		comboBox.setModel(new DefaultComboBoxModel(usus));
		comboBox.setForeground(new Color(0, 0, 31));
		comboBox.setFont(new Font("Roboto", Font.BOLD, 15));
		comboBox.setBorder(null);
		comboBox.addActionListener(this);
		comboBox.setBackground(new Color(255, 102, 0));
		comboBox.setBounds(80, 192, 287, 37);
		contentPanel.add(comboBox);

	}

	private void actualizarLista() {

		DefaultTableModel usuarios = new DefaultTableModel(new Object[][] {},
				new String[] { "Usuario", "Hora", "Conexion" });

		String row;
		table.setModel(usuarios);
		int numCols = usuarios.getColumnCount();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(Server.CSV_NAME));

			while ((row = csvReader.readLine()) != null) {
				// 0: user 1: LocalDateTime 2: C/
				String[] data = row.split(",");
				Object[] fila = new Object[numCols];
//	            	 fila[0]=data[0];
//	        
//		              usuarios.setValueAt(data[0], i,0);
//		             usuarios.setValueAt(data[1], i,1);
//		              usuarios.setValueAt(data[2], i,2);
				usuarios.addRow(data);
				System.out.print(rootPaneCheckingEnabled);
				// do something with the data
			}
			csvReader.close();
		} catch (Exception e) {
			System.out.println("Error al leer el archivo");
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (comboBox == e.getSource()) {
			if (comboBox.getSelectedIndex() != 0) {
				while (table.getModel().getRowCount() > 0) {
					((DefaultTableModel) table.getModel()).removeRow(0);
				}
				DefaultTableModel usuarios = (DefaultTableModel) table.getModel();
				int numCols = usuarios.getColumnCount();
				try {
					BufferedReader csvReader = new BufferedReader(new FileReader(Server.CSV_NAME));
					int i = 0;
					String row;
					while ((row = csvReader.readLine()) != null) {
						String[] data = row.split(",");
						Object[] fila = new Object[numCols];
						if (comboBox.getSelectedItem().toString().equals(data[0])) {
							usuarios.addRow(data);
							System.out.print(rootPaneCheckingEnabled);
							System.out.print(data[0]);
							i++;
						}
					}
					csvReader.close();
				} catch (Exception e1) {
					System.out.println("Error al leer el archivo");
					e1.printStackTrace();
				}

			} else {
				actualizarLista();
			}
		}
		if (volverButton == e.getSource()) {

			dispose();

		}
	}
}
