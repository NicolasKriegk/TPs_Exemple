package org.imie.tarotA.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class EtatPartie {

	private Integer numeroPartie;
	private List<PointTarot> score;
	private List<List<Coup>> plis;
	private Boolean alive;
	private String gagnant;
	private List<Joueur> joueurs;

	public EtatPartie() {
		super();
		joueurs= new ArrayList<Joueur>();
	}

	/**
	 * @return the numeroPartie
	 */
	public Integer getNumeroPartie() {
		return numeroPartie;
	}

	/**
	 * @param numeroPartie
	 *            the numeroPartie to set
	 */
	public void setNumeroPartie(Integer numeroPartie) {
		this.numeroPartie = numeroPartie;
	}

	/**
	 * @return the score
	 */
	public List<PointTarot> getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(List<PointTarot> score) {
		this.score = score;
	}

	/**
	 * @return the plis
	 */
	public List<List<Coup>> getPlis() {
		return plis;
	}

	/**
	 * @param plis
	 *            the plis to set
	 */
	public void setPlis(List<List<Coup>> plis) {
		this.plis = plis;
	}

	/**
	 * @return the alive
	 */
	public Boolean getAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the gagnant
	 */
	public String getGagnant() {
		return gagnant;
	}

	/**
	 * @param gagnant
	 *            the gagnant to set
	 */
	public void setGagnant(String gagnant) {
		this.gagnant = gagnant;
	}

	/**
	 * @return the joueurs
	 */
	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * @param joueurs
	 *            the joueurs to set
	 */
	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

}
