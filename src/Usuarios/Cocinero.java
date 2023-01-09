package Usuarios;

public class Cocinero extends Usuario {
	String categoria;

	/**
	 * @param categoria
	 */
	public Cocinero( int cod_usu,String nom_usu, String ape_usu, String mail, String password) {
		super( cod_usu,nom_usu, ape_usu, mail, password);
		this.categoria="Cocinero";
	}
	
//	public String toString() {
//		return""+getNom_usu()+" "+getApe_usu()+" : "+getCategoria();
//	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
	
		return this.categoria;
	}

}
