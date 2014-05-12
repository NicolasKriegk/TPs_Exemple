package org.imie.IHM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class ConsoleIHM {

	private static ConsoleIHM instance = null;

	private final String PAS_DE_MODIFICATION = "pas de modification";
	private final String SAISIE_OBLIGATOIRE = "saisie obligatoire";
	private final String SELECTIONNER_UN_CURSUS = "selectionner un cursus";
	private final String CURSUS = "cursus";
	private final String PASS_THROUGH = "/";
	private final String SAISIE_INCORRECTE = "saisie incorrecte";
	private final String _2_CANCEL = "2 - Cancel";
	private final String _1_OK = "1 - OK";
	private final String CONFIRMATION_SUPPRESSION = "Confirmation Suppression";
	private final String _3_DELETE = "3 - Delete";
	private final String _2_UPDATE = "2 - Update";
	private final String _1_RETOUR = "1 - Retour";
	private final String QUEL_USER_LIRE = "quel user lire?";
	private final String DATE_FORMAT = "dd/MM/yyyy";
	private final String ENDING_PARENTESIS = ") ";
	private final String STARTIN_PARENTESIS = " (";
	private final String SEPARATEUR_DOUBLE_DOT = " : ";
	private final String DATE_DE_NAISSANCE = "date de naissance";
	private final String COMPETENCES = "competences";
	private final String AGE = "age";
	private final String PRENOM = "prenom";
	private final String NOM = "nom";

	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	// utilisateur courrant de travail de l'application
	private UserDTO currentUserDTO = null;
	// erreur d'application courante de l'application
	private String applicationError = null;
	// ecran courant de l'application
	private Ecran currentEcran = null;

	// initialisation de la variable d'arret de l'application
	private Boolean endApplication = false;

	private ConsoleIHM() {
		super();
	}

	public synchronized static ConsoleIHM getInstance() {
		if (instance == null) {
			instance = new ConsoleIHM();
		}
		return instance;
	}

	public void start() {
		// afectation le 1er écran de l'application
		currentEcran = Ecran.menu_principale;
		// initialisation de la varible qui receuille la saisie dans la console

		while (!endApplication) {
			// instruction d'impression demandant le nettoyage de l'écran (ne
			// marche pas dans eclipse)
			System.out.print("\033[H\033[2J");
			// saut de ligne
			System.out.println("");
			// contrôle de la présence d'une erreur dans l'application
			// gestion de l'erreur d'application
			if (applicationError != null) {
				// affichage de l'erreur d'application
				System.out.println("-------------erreur----------");
				System.out.println(applicationError);
				System.out.println("-----------------------------");
				// réinitialisation de l'erreur d'application pour qu'elle ne
				// soit pas affichée la prochaine fois.
				applicationError = null;
			}

			// affichage de l'écran courant de l'application
			switch (currentEcran) {
			case menu_principale:
				endApplication = afficherEcranMenuPrincipale();
				break;
			case liste_user:
				afficherListeUsers();
				break;
			case formulaire_user:
				afficherFormulaireUser();
				break;
			case formulaire_user_create:
				afficherFormulaireUserCreate();
				break;
			case formulaire_user_update:
				afficherFormulaireUserUpdate();
				break;
			default:
				break;
			}
		}
	}
	
	private void afficherFormulaireUserUpdate() {
		IUserService userService = BaseConcreteFactory.getInstance().createUserService(null);
		String s;
		// affichage du formulaire du user
		// utilisateur courrant de travail de l'application utilisé sans
		// avoir besoin d'utiliser un DAO

		System.out.println(NOM + SEPARATEUR_DOUBLE_DOT + STARTIN_PARENTESIS + currentUserDTO.getNom()
				+ ENDING_PARENTESIS + SEPARATEUR_DOUBLE_DOT);
		s = null;
		while (s == null) {
			s = currentUserDTO.getNom();
			try {
				s = getStringInput();
			} catch (PassThroughInputException e) {
				// catch vide normal
			}
			if (s == null) {
				System.out.println(SAISIE_OBLIGATOIRE);
			}
		}
		currentUserDTO.setNom(s);

		System.out.println(PRENOM + SEPARATEUR_DOUBLE_DOT + STARTIN_PARENTESIS + currentUserDTO.getPrenom()
				+ ENDING_PARENTESIS + SEPARATEUR_DOUBLE_DOT);
		try {
			s = getStringInput();
			currentUserDTO.setPrenom(s);
		} catch (PassThroughInputException e) {
			// catch vide normal
			System.out.println(e.getMessage());
		}
		String dateUser2 = "";
		if (currentUserDTO.getDateNaiss() != null) {
			dateUser2 = dateFormat.format(currentUserDTO.getDateNaiss());
		}
		System.out.println(DATE_DE_NAISSANCE + SEPARATEUR_DOUBLE_DOT + STARTIN_PARENTESIS + dateUser2
				+ ENDING_PARENTESIS + SEPARATEUR_DOUBLE_DOT);
		Date inputDateNaiss = null;
		try {
			inputDateNaiss = getDateInput();
			currentUserDTO.setDateNaiss(inputDateNaiss);
		} catch (PassThroughInputException e) {
			// catch vide normal
			System.out.println(e.getMessage());
		}

		s = "";
		if (currentUserDTO.getCursus() != null) {
			s = currentUserDTO.getCursus().getLibelle();
		}
		System.out.println(CURSUS + SEPARATEUR_DOUBLE_DOT + STARTIN_PARENTESIS + s + ENDING_PARENTESIS);

		try {
			CursusDTO cursusDTOselected;
			try {
				cursusDTOselected = selectCursus();
				currentUserDTO.setCursus(cursusDTOselected);
			} catch (PassThroughInputException e) {
				// catch vide normal
				System.out.println(e.getMessage());
			}

			userService.updateUser(currentUserDTO);
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		} catch (ServiceException e) {
			ExceptionManager.getInstance().manageException(e);
		}

		currentEcran = Ecran.formulaire_user;
	}

	private void afficherFormulaireUserCreate() {
		IUserService userService = BaseConcreteFactory.getInstance().createUserService(null);
		String s;
		// affichage du formulaire du user
		// utilisateur courrant de travail de l'application utilisé sans
		// avoir besoin d'utiliser un DAO
		UserDTO newUser = new UserDTO();

		System.out.println(NOM + SEPARATEUR_DOUBLE_DOT);
		s = null;
		while (s == null) {
			try {
				s = getStringInput();
			} catch (PassThroughInputException e) {
				System.out.println(SAISIE_OBLIGATOIRE);
			}
		}

		newUser.setNom(s);

		System.out.println(PRENOM + SEPARATEUR_DOUBLE_DOT);
		try {
			newUser.setPrenom(getStringInput());
		} catch (PassThroughInputException e) {
			// null si exeption passthrough : catch vide normal
			System.out.println(e.getMessage());
		}
		System.out.println(DATE_DE_NAISSANCE + SEPARATEUR_DOUBLE_DOT);
		try {
			newUser.setDateNaiss(getDateInput());
		} catch (PassThroughInputException e) {
			// null si exeption passthrough : catch vide normal
			System.out.println(e.getMessage());
		}
		System.out.println(CURSUS + SEPARATEUR_DOUBLE_DOT);
		try {
			CursusDTO cursusDTOselected = null;
			try {
				cursusDTOselected = selectCursus();
			} catch (PassThroughInputException e) {
				// null si exeption passthrough : catch vide normal
				System.out.println(e.getMessage());
			}
			newUser.setCursus(cursusDTOselected);
			currentUserDTO = userService.insertUser(newUser);
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		} catch (ServiceException e) {
			ExceptionManager.getInstance().manageException(e);
		}

		currentEcran = Ecran.formulaire_user;
	}

	private void afficherFormulaireUser() {
		IUserService userService = BaseConcreteFactory.getInstance().createUserService(null);
		// affichage du formulaire du user
		// utilisateur courrant de travail de l'application utilisé sans
		// avoir besoin d'utiliser un DAO
		System.out.println(NOM + SEPARATEUR_DOUBLE_DOT + currentUserDTO.getNom());
		System.out.println(PRENOM + SEPARATEUR_DOUBLE_DOT + currentUserDTO.getPrenom());
		String dateUser = "";
		if (currentUserDTO.getDateNaiss() != null) {
			dateUser = dateFormat.format(currentUserDTO.getDateNaiss());
		}
		System.out.println(DATE_DE_NAISSANCE + SEPARATEUR_DOUBLE_DOT + dateUser);
		System.out.println(AGE + SEPARATEUR_DOUBLE_DOT + currentUserDTO.getAge());
		CursusDTO cursusDTO = currentUserDTO.getCursus();
		
		String cursusUser = null;
		if (cursusDTO != null) {
			cursusUser = cursusDTO.getLibelle();
		}
		System.out.println(CURSUS + SEPARATEUR_DOUBLE_DOT + cursusUser);
		System.out.println(COMPETENCES + SEPARATEUR_DOUBLE_DOT);
		for (CompetenceDTO competenceDTO : currentUserDTO.getCompetences()) {
			System.out.println("{libelle:" + competenceDTO.getLibelle() + ";niveau:"
					+ competenceDTO.getNiveau() + "}");
		}
		// affichage du menu
		System.out.println(_1_RETOUR);
		System.out.println(_2_UPDATE);
		System.out.println(_3_DELETE);
		// récupération du menu à utiliser
		Integer choix = null;
		try {
			choix = getIntegerInput();
		} catch (PassThroughInputException e2) {
			ExceptionManager.getInstance().manageException(e2);
		}

		// execution du menu utilisé
		if (choix != null) {
			switch (choix) {
			case 1:
				// changement d'écran
				currentEcran = Ecran.liste_user;
				break;
			case 2:
				// changement d'écran
				currentEcran = Ecran.formulaire_user_update;
				break;
			case 3:
				System.out.println(CONFIRMATION_SUPPRESSION);
				System.out.println(_1_OK);
				System.out.println(_2_CANCEL);
				Integer choixConfirm =null;
				try {
					choixConfirm = getIntegerInput();
				} catch (PassThroughInputException e2) {
					ExceptionManager.getInstance().manageException(e2);
				}
				if (choixConfirm != null && choixConfirm.equals(1)) {
					try {
						userService.deleteUser(currentUserDTO);
						// changement d'écran
						currentEcran = Ecran.liste_user;
					} catch (TransactionalConnectionException e) {
						ExceptionManager.getInstance().manageException(e);
					} catch (ServiceException e) {
						ExceptionManager.getInstance().manageException(e);
					}
				}
				break;

			default:
				break;
			}
		}
	}

	private void afficherListeUsers() {
		IUserService userService = BaseConcreteFactory.getInstance().createUserService(null);
		Integer choix;
		// initialisation de la liste des users à afficher
		List<UserDTO> userDTOs = null;

		// récupération des users de la base de données sous forme
		// de DTO
		// cette methode récupère une grappe de DTO qui inclue les
		// compétences
		try {
			userDTOs = userService.getUsers();
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		} catch (ServiceException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		// initialisation du compteur de ligne
		int i = 1;
		// parcours de la liste des users à afficher
		for (UserDTO userDTO : userDTOs) {
			// affichage de chaque user en exploitant les attributs
			// des DTO
			System.out.println(i + " {identité:" + userDTO.getNom() + " " + userDTO.getPrenom() + ";age:"
					+ userDTO.getAge() + "}");
			// incrémentation du compteur de ligne
			i++;
		}

		// affichage du menu
		System.out.println("1 - read");
		System.out.println("2 - retour");
		// récupération du menu à utiliser
		try {
			choix = getIntegerInput();

			// execution du menu utilisé
			if (choix != null) {
				switch (choix) {
				case 1:
					// récupération de la ligne à afficher
					System.out.println(QUEL_USER_LIRE);
					Integer numLigneRead = getIntegerInput();
					// récupération du UserDTO correspondant à la ligne
					// demandée
					// affectation de l'utilisateur courrant de travail
					// de
					// l'application
					currentUserDTO = userDTOs.get(numLigneRead - 1);
					if (currentUserDTO != null) {
						// changement d'écran
						currentEcran = Ecran.formulaire_user;
					}
					break;
				case 2:
					// changement d'écran
					currentEcran = Ecran.menu_principale;
					break;
				default:
					break;
				}
			}
		} catch (PassThroughInputException e2) {
			ExceptionManager.getInstance().manageException(e2);
		}
	}

	private Boolean afficherEcranMenuPrincipale() {
		Integer choix;
		// affichage du menu principale de l'application
		System.out.println("1 - liste des users");
		System.out.println("2 - créer un users");
		System.out.println("3 - close");
		// récupération du menu à utiliser
		try {
			choix = getIntegerInput();

			// execution du menu utilisé
			if (choix != null) {
				switch (choix) {
				case 1:
					// changement d'écran
					currentEcran = Ecran.liste_user;
					break;
				case 2:
					// changement d'écran
					currentEcran = Ecran.formulaire_user_create;

					break;
				case 3:
					// déclaration de fin d'application
					endApplication = true;
					break;
				default:
					break;
				}
			}
		} catch (PassThroughInputException e2) {
			// aucun menu saisie. catch vide normale
		}
		return endApplication;
	}

	private CursusDTO selectCursus() throws PassThroughInputException {
		ICursusService cursusService = BaseConcreteFactory.getInstance().createCursusService(null);
		System.out.println(SELECTIONNER_UN_CURSUS);
		List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
		try {
			cursusDTOs = cursusService.findAll();
		} catch (ServiceException e) {
			ExceptionManager.getInstance().manageException(e);
		} catch (TransactionalConnectionException e) {
			ExceptionManager.getInstance().manageException(e);
		}
		Integer j = 1;
		for (CursusDTO cursusDTO : cursusDTOs) {
			System.out.println(j + "{libelle:" + cursusDTO.getLibelle() + "}");
			j++;
		}
		Integer selectedCursusIndex = getIntegerInput();
		CursusDTO cursusDTOselected = null;
		if (selectedCursusIndex != null) {
			cursusDTOselected = cursusDTOs.get(selectedCursusIndex - 1);
		}
		return cursusDTOselected;
	}

	private Date getDateInput() throws PassThroughInputException {
		String dateNaissString;
		dateNaissString = getStringInput();

		Date dateNaissDate = null;
		if (dateNaissString != null) {
			try {
				dateNaissDate = dateFormat.parse(dateNaissString);
			} catch (ParseException e1) {
				applicationError = SAISIE_INCORRECTE;
			}
		}
		return dateNaissDate;
	}

	private String getStringInput() throws PassThroughInputException {
		BufferedReader bufferRead;
		String s = null;
		bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			s = bufferRead.readLine();
		} catch (IOException e) {
			applicationError = SAISIE_INCORRECTE;
		}
		if (s != null && s.equals(PASS_THROUGH)) {
			throw new PassThroughInputException(PAS_DE_MODIFICATION);
		}
		if (s != null && s.equals("§")) {
			s = null;
		}
		return s;
	}

	/**
	 * methode utilitaire de saisie d'un chiffre avec gestion de l'erreur
	 * d'application
	 * 
	 * @return Integer saisie du clavier convertie en Integer + gestion de
	 *         l'erreur d'application
	 */
	private Integer getIntegerInput() throws PassThroughInputException {

		// initialisation de la chaîne de caractère récupérant la saisie de la
		// console
		String s = getStringInput();
		// initialisation du numérique récupérant la saisie de la console
		Integer choix = null;
		try {

			// évaluation de la chaîne de caractère pour en extraire un
			// numérique
			choix = Integer.valueOf(s);
		} catch (NumberFormatException e) {
			// gestion de l'exception de parse de la chaîne de caractère pour
			// obtenir l'Integer
			// affectation de l'erreur d'application
			applicationError = SAISIE_INCORRECTE;
		}
		// retour du Integer
		return choix;
	}
}
