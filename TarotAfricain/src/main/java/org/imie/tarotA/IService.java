package org.imie.tarotA;

import org.imie.tarotA.model.EtatPartie;

public interface IService {
	public void majEtat(EtatPartie etatPartie);
	public EtatPartie  getEtatPartie();
	public void initParti(Integer NumeroPartie, EtatPartie etatPartie);

}
