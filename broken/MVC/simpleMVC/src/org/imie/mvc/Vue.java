package org.imie.mvc;

import java.text.MessageFormat;
import java.util.Scanner;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class Vue {
	public class RefreshThread implements Runnable {
		private Boolean activ = true;

		public void stop(){
			activ=false;
		}
		
		@Override
		public void run() {
			while (activ) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				affichage();
			}
			
			System.out.println("End RefreshThread");
		}
	}

	private Model model;
	private Controller controller;

	private String commande = "";

	private RefreshThread thread = new RefreshThread();
	private Scanner scanIn = new Scanner(System.in);

	private Screen screen;

	public void affichage() {

		// System.out.print("\033[2J\033[;H");
		// terminal.clearScreen();
		Key key = screen.readInput();
		if (key != null) {
			// thread.activ=false;
			if (key.getKind().equals(Key.Kind.Enter)) {
				traiterCommande(commande);
				commande = "";
			} else if (key.getKind().equals(Key.Kind.Backspace)) {
				if (commande.length() > 0) {
					commande = commande.substring(0, commande.length() - 1);
				}
			} else {
				commande += key.getCharacter();
			}
		}

		MessageFormat formater = new MessageFormat(
				"{0,number,00} : {1,number,00} : {2,number,00}");
		Object[] data = { model.getHeure(), model.getMinute(),
				model.getSecond() };
		formater.format(data);
		// System.out.println(formater.format(data));
		screen.clear();

		screen.putString(0, 1, formater.format(data), Terminal.Color.WHITE,
				Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
		screen.putString(0, 3, commande, Terminal.Color.WHITE,
				Terminal.Color.BLACK);

		screen.refresh();

		// terminal.flush();

		// String toto = scanIn.nextLine();

	}

	private void traiterCommande(String commande2) {
		System.out.println(commande2);

		if ("+s".equals(commande2)) {
			controller.incrementSecond();
		}

		if ("+m".equals(commande2)) {
			controller.incrementMinute();
		}

		if ("+h".equals(commande2)) {
			controller.incrementHeure();
		}

		if ("-s".equals(commande2)) {
			controller.decrementSecond();
		}

		if ("-m".equals(commande2)) {
			controller.decrementMinute();
		}

		if ("-h".equals(commande2)) {
			controller.decrementHeure();
		}
		
		if ("/q".equals(commande2)) {
			screen.stopScreen();
			thread.stop();
			controller.stop();
		}

	}

	public Vue() {
		super();
		screen = TerminalFacade.createScreen();
		screen.startScreen();
		screen.putString(10, 5, "Hello Lanterna!", Terminal.Color.RED,
				Terminal.Color.GREEN, ScreenCharacterStyle.Bold);
		screen.refresh();

		//
		// terminal = TerminalFacade.createTerminal(System.in, System.out,
		// Charset.forName("UTF8"));
		// terminal.applyForegroundColor(Terminal.Color.RED);
		// terminal.enterPrivateMode();
		//
		// System.out.print('c');
		// terminal.putCharacter('t');
		// terminal.flush();
		// terminal.exitPrivateMode();

		Thread t = new Thread(thread);
		t.start();
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

}
