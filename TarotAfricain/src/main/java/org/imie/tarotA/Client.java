package org.imie.tarotA;

import java.util.List;

import org.imie.tarotA.model.EtatPartie;
import org.imie.tarotA.model.Joueur;

public class Client {
	IService service;

	public void setService(IService service) {
		this.service = service;
	}

	public void rejoindreParti(String nom) {
		EtatPartie etatPartie = service.getEtatPartie();
		Joueur joueur = new Joueur();
		joueur.setNom(nom);
		etatPartie.getJoueurs().add(joueur);
		service.majEtat(etatPartie);
	}

	public List<Joueur> listeJoueur() {
		EtatPartie etatPartie = service.getEtatPartie();
		return etatPartie.getJoueurs();
	}

}
