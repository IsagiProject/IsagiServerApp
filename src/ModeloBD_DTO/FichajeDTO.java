package ModeloBD_DTO;

import java.sql.Timestamp;

public class FichajeDTO {
    private int idFichaje;
    private int idUsuario;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;

    public FichajeDTO(int idFichaje, int idUsuario, Timestamp fechaInicio, Timestamp fechaFin) {
        this.idFichaje = idFichaje;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdFichaje() {
        return idFichaje;
    }

    public void setIdFichaje(int idFichaje) {
        this.idFichaje = idFichaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

}
