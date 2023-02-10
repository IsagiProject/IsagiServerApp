package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ModeloBD_DAO.FichajeDAO;
import ModeloBD_DAO.UsuarioDAO;
import ModeloBD_DTO.FichajeDTO;
import ModeloBD_DTO.UsuarioDTO;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class VerFichajes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JComboBox comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            VerFichajes dialog = new VerFichajes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public VerFichajes() {
        setBounds(100, 100, 740, 492);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(10, 10, 31));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 117, 672, 267);
        contentPanel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Fichaje", "ID Usuario", "Fecha Inicio", "Fecha Fin"
                }));
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        table.setShowGrid(false);
        table.setForeground(new Color(255, 112, 10));
        scrollPane.setViewportView(table);
        table.setFont(new Font("Roboto", Font.PLAIN, 12));
        table.setBackground(new Color(20, 20, 41));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.setColumnSelectionAllowed(true);

        table.setCellSelectionEnabled(true);
        scrollPane.setViewportView(table);

        comboBox = new JComboBox();
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mail = comboBox.getSelectedItem().toString();
                System.out.println(mail);
                ArrayList<FichajeDTO> listaFichajes = null;
                if (mail.equals("TODOS")) {
                    listaFichajes = new FichajeDAO().listarTodos();
                } else {
                    listaFichajes = new FichajeDAO().listarPorUsu(mail);
                }

                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                while (modelo.getRowCount() > 0)
                    modelo.removeRow(0);

                for (FichajeDTO fichaje : listaFichajes) {
                    ((DefaultTableModel) table.getModel()).addRow(new Object[] {
                            fichaje.getIdFichaje(),
                            fichaje.getIdUsuario(),
                            fichaje.getFechaInicio(),
                            fichaje.getFechaFin()
                    });
                }
            }
        });
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboBox.setBorder(null);
        comboBox.setForeground(new Color(0, 0, 51));
        comboBox.setFont(new Font("Roboto", Font.BOLD, 15));
        comboBox.setBackground(new Color(255, 102, 0));

        comboBox.setBounds(549, 60, 151, 46);
        contentPanel.add(comboBox);

        comboBox.addItem("TODOS");

        JButton btGenerarInforme = new JButton("Generar informe");
        btGenerarInforme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre el visor de Jasper Reports que nos permite descargarlo
                JasperPrint jasperPrintWindow = null;
                try {
                    jasperPrintWindow = JasperFillManager.fillReport("src/Visual/Coffee.jasper", null,
                            Conexiones.Conexion.getInstancia().getCon());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow, false);
                jasperViewer.setVisible(true);
            }
        });
        btGenerarInforme.setForeground(new Color(10, 10, 31));
        btGenerarInforme.setFont(new Font("Roboto", Font.BOLD, 19));
        btGenerarInforme.setBorder(null);
        btGenerarInforme.setBackground(new Color(255, 112, 10));
        btGenerarInforme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btGenerarInforme.setBackground(new Color(10, 10, 31));
                btGenerarInforme.setForeground(new Color(255, 112, 10));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btGenerarInforme.setBackground(new Color(255, 112, 10));
                btGenerarInforme.setForeground(new Color(10, 10, 31));

            }
        });

        btGenerarInforme.setBounds(333, 60, 196, 46);
        contentPanel.add(btGenerarInforme);
        ArrayList<UsuarioDTO> listaUsuarios = new UsuarioDAO().listarTodos();
        for (UsuarioDTO usuario : listaUsuarios) {
            comboBox.addItem(usuario.getMail());
        }
    }
}
