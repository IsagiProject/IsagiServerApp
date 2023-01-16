package ModeloBD_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Conexiones.Conexion;
import ModeloBD_DTO.UsuarioDTO;

public class UsuarioDAO implements PatronDAO<UsuarioDTO> {

    private static final String SQL_INSERT = "INSERT INTO USUARIOS (mail, nom_usu, ape_usu, password, categoria) VALUES (?,?,?,?,?);";
    private static final String SQL_DELETE = "DELETE FROM USUARIOS WHERE cod_usu = ?;";
    private static final String SQL_UPDATE = "UPDATE USUARIOS SET mail = ?, nom_usu = ?, ape_usu = ?, password = ?, categoria = ? WHERE cod_usu = ?;";
    private static final String SQL_FIND = "SELECT * FROM USUARIOS WHERE cod_usu = ?;";
    private static final String SQL_FIND_ALL = "SELECT * FROM USUARIOS;";
    private static final Connection con = Conexion.getInstancia().getCon();

    @Override
    public Object insertar(UsuarioDTO t) {
        try {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getMail());
            ps.setString(2, t.getNom_usu());    
            ps.setString(3, t.getApe_usu());
            ps.setString(4, t.getPassword());
            ps.setString(5, t.getCategoria());
            System.out.println("[Insertar] Trace" + Thread.currentThread().getStackTrace());
            System.out.println("[Insertar] Registros: " + this.listarTodos().size());
            System.out.println("[Insertar] Datos: " + t);
            if (ps.executeUpdate() > 0) {
                return ps.getGeneratedKeys();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean borrar(Object pk) {
        try {
            PreparedStatement ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, (int) pk);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean actualizar(UsuarioDTO t) {
        try {
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getMail());
            ps.setString(2, t.getNom_usu());
            ps.setString(3, t.getApe_usu());
            ps.setString(4, t.getPassword());
            ps.setString(5, t.getCategoria());
            ps.setInt(6, t.getCod_usu());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UsuarioDTO buscar(Object pk) {
        try {
            PreparedStatement ps = con.prepareStatement(SQL_FIND);
            ps.setInt(1, (int) pk);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                return new UsuarioDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<UsuarioDTO> listarTodos() {
        ArrayList<UsuarioDTO> lista = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new UsuarioDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
