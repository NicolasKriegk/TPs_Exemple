package org.imie.mvc;
import java.util.Scanner;

public class Launcher {
	private static Model model;
	private static Controller controller;
	private static Vue vue;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		model = new Model();
		vue = new Vue();
		controller = new Controller();
		vue.setModel(model);
		vue.setController(controller);
		controller.setModel(model);
		controller.setVue(vue);
//		
//		System.out.println("Enter something here : ");
//
//		String sWhatever;
//
//		Scanner scanIn = new Scanner(System.in);
//		sWhatever = scanIn.nextLine();
//
//		
//		System.out.println(sWhatever);
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println();	
//		}
//		
//		System.out.println("------------------------------------");
//		
//		
//		System.out.println("Enter something here : ");
//		sWhatever = scanIn.nextLine();
//
//		
//		System.out.println(sWhatever);
//		scanIn.close();
	}

}
