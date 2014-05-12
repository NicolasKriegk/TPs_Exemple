package test.java;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import main.java.Jeu;

import org.junit.Test;

public class TennisTests {

	@Test
	public void testInitialisationNouveauJeu() {
		new Jeu();
	}

	@Test
	public void testAfficherScoreDebutDePartie() {
		Jeu jeu = new Jeu();
		assertThat(jeu.score(), equalTo("0-0"));
	}
	
	@Test
	public void testJoueur1Remporte1Echange(){
		Jeu jeu = new Jeu();
		jeu.joueur1Marque();
		assertThat(jeu.score(), equalTo("15-0"));		
	}
	
	@Test
	public void testJoueur1Remporte2Echange(){
		Jeu jeu = new Jeu();
		jeu.joueur1Marque();
		jeu.joueur1Marque();
		assertThat(jeu.score(), equalTo("30-0"));		
	}
	
	



}
