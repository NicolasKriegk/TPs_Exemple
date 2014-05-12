package org.imie;

import org.imie.IHM.ConsoleIHM;

/**
 * classe principale de l'application contenant le main
 * 
 * @author imie
 * 
 */
public class Launcher {

	/**
	 * point d'entrée de l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleIHM.getInstance().start();
	}

	
}
