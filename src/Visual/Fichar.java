package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Funciones.funcLogin;
import ModeloBD_DAO.FichajeDAO;
import ModeloBD_DAO.UsuarioDAO;

public class Fichar extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public Fichar() {
        setBounds(100, 100, 472, 353);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(10, 10, 31));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JButton btFichar = new JButton("FICHAR");
        btFichar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = fichar();
                if (result == 0) {
                    btFichar.setText("FICHAR");
                } else if (result == 1) {
                    btFichar.setText("CERRAR");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al fichar");
                }
            }
        });
        btFichar.setForeground(new Color(10, 10, 31));
        btFichar.setFont(new Font("Roboto", Font.BOLD, 19));
        btFichar.setBorder(null);
        btFichar.setBackground(new Color(255, 112, 10));
        btFichar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btFichar.setBackground(new Color(10, 10, 31));
                btFichar.setForeground(new Color(255, 112, 10));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btFichar.setBackground(new Color(255, 112, 10));
                btFichar.setForeground(new Color(10, 10, 31));

            }
        });
        btFichar.setBounds(121, 92, 208, 53);
        contentPanel.add(btFichar);

        JLabel lblNewLabel = new JLabel("FICHAJES");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(new Color(25, 25, 45));
        lblNewLabel.setForeground(new Color(235, 112, 10));
        lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 28));
        lblNewLabel.setBounds(110, 27, 208, 35);
        contentPanel.add(lblNewLabel);

        JButton btVolver = new JButton("VOLVER");
        btVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (!funcLogin.categoria.equals("Gerente")) {
                    try {
                        new Login().setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btVolver.setForeground(new Color(10, 10, 31));
        btVolver.setFont(new Font("Dialog", Font.BOLD, 19));
        btVolver.setBorder(null);
        btVolver.setBackground(new Color(255, 112, 10));
        btVolver.setBounds(316, 249, 130, 43);
        contentPanel.add(btVolver);

        JButton btVerFichajes = new JButton("VER FICHAJES");
        btVerFichajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerFichajes().setVisible(true);
            }
        });
        btVerFichajes.setForeground(new Color(10, 10, 31));
        btVerFichajes.setFont(new Font("Roboto", Font.BOLD, 19));
        btVerFichajes.setBorder(null);
        btVerFichajes.setBackground(new Color(255, 112, 10));
        btVerFichajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btVerFichajes.setBackground(new Color(10, 10, 31));
                btVerFichajes.setForeground(new Color(255, 112, 10));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btVerFichajes.setBackground(new Color(255, 112, 10));
                btVerFichajes.setForeground(new Color(10, 10, 31));

            }
        });
        btVerFichajes.setBounds(121, 160, 208, 53);

        if (funcLogin.categoria.equals("Gerente")) {
            contentPanel.add(btVerFichajes);
        }

        int estado = new FichajeDAO().getEstadoFichar(new UsuarioDAO().buscar(funcLogin.idUsuario).getCod_usu());
        if (estado == 1) {
            btFichar.setText("CERRAR");
        } else if (estado == 0) {
            btFichar.setText("FICHAR");
        } else {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        }
    }

    public int fichar() {
        int idUsuario = new UsuarioDAO().buscar(funcLogin.idUsuario).getCod_usu();
        return new FichajeDAO().fichar(idUsuario);
    }
}
