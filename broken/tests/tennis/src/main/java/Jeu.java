package main.java;


import java.util.ArrayList;
import java.util.List;

public class Jeu {
	
	public Integer scoreJoueur1;
	
	public List<String> scoreValue = new ArrayList<String>();

	public String score() {
//		String scoreTextJoueur1 = "0";
//		if (scoreJoueur1==0){
//			scoreTextJoueur1="0";
//		}else if(scoreJoueur1==1){
//			scoreTextJoueur1="15";
//		}else if(scoreJoueur1==2){
//			scoreTextJoueur1="30";
//		}
		return scoreValue.get(scoreJoueur1).concat("-0");
	}

	public Jeu() {
		super();
		scoreJoueur1=0;
		scoreValue = new ArrayList<String>();
		scoreValue.add("0");
		scoreValue.add("15");
		scoreValue.add("30");
	}

	public void joueur1Marque() {
		scoreJoueur1++;	
	}

}
