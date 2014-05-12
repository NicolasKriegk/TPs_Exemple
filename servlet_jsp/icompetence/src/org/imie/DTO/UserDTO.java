package org.imie.DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * DTO représantant un utilisateur
 * 
 * @author imie
 * 
 */
public class UserDTO {

	// attributs de classe
	private Date dateNaiss;
	private Integer id;
	private String nom;
	private String prenom;
	private List<CompetenceDTO> competences;
	private CursusDTO cursus;
	private String login;

	// constructeur
	// Ce constructeur par défaut est necessaire pour initialiser la liste des
	// compétence pour être sur que cette liste ne soit pas vide lors de
	// l'instanciation d'un nouvel objet
	public UserDTO() {
		super();
		// initialisation de la liste des compétences
		competences = new ArrayList<CompetenceDTO>();
	}

	// accesseurs
	public Integer getAge() {
		Integer age = null;
		if (getDateNaiss() != null) {
			Calendar dob = Calendar.getInstance();
			dob.setTime(getDateNaiss());
			Calendar today = Calendar.getInstance();
			age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
			if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
				age--;
			} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
					&& today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
				age--;
			}
		}
		return age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// remplacement du setCompetence par les methodes de l'API List
	public void addCompetence(CompetenceDTO competenceDTO) {
		this.competences.add(competenceDTO);
	}

	// remplacement du setCompetence par les methodes de l'API List
	public void removeCompetence(CompetenceDTO competenceDTO) {
		this.competences.remove(competenceDTO);
	}

	public List<CompetenceDTO> getCompetences() {
		return competences;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public CursusDTO getCursus() {
		return cursus;
	}

	public void setCursus(CursusDTO cursus) {
		this.cursus = cursus;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	

}
