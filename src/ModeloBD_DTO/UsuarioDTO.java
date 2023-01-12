package ModeloBD_DTO;

import java.io.Serializable;

import Usuarios.Persona;

public class UsuarioDTO extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	int cod_usu;
	String nom_usu;
	String ape_usu;
	String password;
	String categoria;
	String mail;

	public UsuarioDTO() {
		this.cod_usu = 0;
	};

	public UsuarioDTO(int cod_usu, String nom_usu, String ape_usu, String mail, String password) {
		this.mail = mail;
		this.cod_usu = cod_usu;
		this.nom_usu = nom_usu;
		this.ape_usu = ape_usu;
		this.password = password;
	}

	public UsuarioDTO(int cod_usu, String nom_usu, String ape_usu, String mail, String password, String categoria) {
		this.mail = mail;
		this.cod_usu = cod_usu;
		this.nom_usu = nom_usu;
		this.ape_usu = ape_usu;
		this.password = password;
		this.categoria = categoria;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getApe_usu() {
		return ape_usu;
	}

	public void setApe_usu(String ape_usu) {
		this.ape_usu = ape_usu;
	}

	public int getCod_usu() {
		return cod_usu;
	}

	public void setCod_usu(int cod_usu) {
		this.cod_usu = cod_usu;
	}

	public String getNom_usu() {
		return nom_usu;
	}

	public void setNom_usu(String nom_usu) {
		this.nom_usu = nom_usu;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "" + this.cod_usu + "- " + this.nom_usu + " " + this.ape_usu;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
