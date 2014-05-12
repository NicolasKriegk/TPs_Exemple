package org.imie.DTO;

/**
 * DTO représentant une copétence. Ce DTO est concu en fonction des besoins de
 * l'interface Il ne peut pas être utilisé pour afficher les niveaux des Users
 * à partir d'une compétence par ex.
 * 
 * @author imie
 * 
 */
public class CompetenceDTO {
	
	//attributs de classe
	private String libelle;
	private Integer niveau;

	
	//accesseurs
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}

}
