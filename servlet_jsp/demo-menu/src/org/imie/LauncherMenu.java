package org.imie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LauncherMenu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ecran currentEcran = Ecran.ecran1;
		Boolean endApplication =false;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		while (!endApplication){
			System.out.print("\033[H\033[2J");
			System.out.println("");
			switch (currentEcran) {
			case ecran1:
				//contenu de l'ecran1
				System.out.println("ecran 1");
				System.out.println("1 - ecran 2");
				System.out.println("2 - ecran 3");
				System.out.println("3 - close");
				
				try {
					 s = bufferRead.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if (s!=null){
			    	Integer choix = Integer.valueOf(s);
			    	switch (choix) {
					case 1:
						currentEcran = Ecran.ecran2;
						break;
					case 2:
						currentEcran = Ecran.ecran3;
						break;
					case 3:
						endApplication=true;
						break;
					default:
						break;
					}
			    }
			    break;
			case ecran2:
				//contenu de l'ecran2
				System.out.println("ecran 2");
				System.out.println("1 - retour");
				try {
					 s = bufferRead.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if (s!=null){
			    	Integer choix = Integer.valueOf(s);
			    	switch (choix) {
					case 1:
						currentEcran=Ecran.ecran1;
						break;
					default:
						break;
					}
			    }
			    break;
			case ecran3:
				System.out.println("ecran 3");
				System.out.println("1 - retour");
				try {
					 s = bufferRead.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if (s!=null){
			    	Integer choix = Integer.valueOf(s);
			    	switch (choix) {
					case 1:
						currentEcran=Ecran.ecran1;
						break;
					default:
						break;
					}
			    }
			    break;
			default:
				break;
			}
			
		}

	}

}
