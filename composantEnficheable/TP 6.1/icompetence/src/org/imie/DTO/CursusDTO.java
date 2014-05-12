package org.imie.DTO;

/**
 * DTO représentant un cursus. Un cursus est une formation de l'école.
 * @author imie
 *
 */
public class CursusDTO {
	private Integer id ;
	private String libelle;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

}
