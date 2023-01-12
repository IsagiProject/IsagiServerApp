package Usuarios;

import ModeloBD_DTO.UsuarioDTO;

public class Gerente extends UsuarioDTO {
	private static final long serialVersionUID = 1L;

	String categoria;
	public Gerente( int cod_usu,String nom_usu, String ape_usu, String mail, String password) {
		super( cod_usu,nom_usu, ape_usu, mail, password);
		this.categoria="Gerente";
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
//	public String toString() {
//		return""+getNom_usu()+" "+getApe_usu()+" : "+getCategoria();
//	}

}
