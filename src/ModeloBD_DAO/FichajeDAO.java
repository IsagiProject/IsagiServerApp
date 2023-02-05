package ModeloBD_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Conexiones.Conexion;
import ModeloBD_DTO.FichajeDTO;

public class FichajeDAO implements PatronDAO<FichajeDTO> {

    private static final String SQL_ESTA_DENTRO = "SELECT * FROM FICHAJES WHERE id_usuario = ? AND fecha_fin IS NULL;";
    private static final String SQL_FICHAR_ENTRAR = "INSERT INTO FICHAJES (id_usuario) VALUES (?);";
    private static final String SQL_FICHAR_SALIR = "UPDATE FICHAJES set fecha_fin=CURRENT_TIMESTAMP WHERE id_usuario = ? AND fecha_fin IS NULL;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM FICHAJES;";
    private static final String SQL_SELECT_BY_USER = "SELECT * FROM FICHAJES WHERE id_usuario IN (SELECT cod_usu FROM USUARIOS WHERE mail = ?);";
    private static final Connection con = Conexion.getInstancia().getCon();

    public int fichar(int idUsuario) {
        try {
            PreparedStatement ps;
            int estado = getEstadoFichar(idUsuario);

            // ESTA DENTRO. HAY QUE CERRAR
            if (estado == 1) {
                ps = con.prepareStatement(SQL_FICHAR_SALIR);
                ps.setInt(1, idUsuario);
                ps.executeUpdate();
                return 0;
            } else {
                ps = con.prepareStatement(SQL_FICHAR_ENTRAR);
                ps.setInt(1, idUsuario);
                ps.executeUpdate();
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int getEstadoFichar(int idUsuario) {
        try {
            PreparedStatement ps = con.prepareStatement(SQL_ESTA_DENTRO);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Object insertar(FichajeDTO t) {
        return null;
    }

    @Override
    public boolean borrar(Object pk) {
        return false;
    }

    @Override
    public boolean actualizar(FichajeDTO t) {
        return false;
    }

    @Override
    public FichajeDTO buscar(Object pk) {
        return null;
    }

    @Override
    public ArrayList<FichajeDTO> listarTodos() {
        ArrayList<FichajeDTO> lista = new ArrayList<FichajeDTO>();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FichajeDTO fichaje = new FichajeDTO(rs.getInt("id_fichaje"), rs.getInt("id_usuario"),
                        rs.getTimestamp("fecha_inicio"), rs.getTimestamp("fecha_fin"));
                lista.add(fichaje);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<FichajeDTO> listarPorUsu(String mail) {
        ArrayList<FichajeDTO> lista = new ArrayList<FichajeDTO>();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_USER);
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FichajeDTO fichaje = new FichajeDTO(rs.getInt("id_fichaje"), rs.getInt("id_usuario"),
                        rs.getTimestamp("fecha_inicio"), rs.getTimestamp("fecha_fin"));
                lista.add(fichaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
