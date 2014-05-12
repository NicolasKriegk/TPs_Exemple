/**
 * 
 */
package org.imie.IHM;

/**
 * Exception conçue pour être levée lors d'une saisie console dédié à passé la
 * saisie demandée
 * 
 * @author imie
 * 
 */
public class PassThroughInputException extends Exception {

	/**
	 * le fait d'hériter de Exception fait que nous implémentons Serializable et
	 * cela impose de redéfinir cet attribut qui sert en cas de sérialisation de
	 * l'objet
	 */
	private static final long serialVersionUID = 327615550986652951L;

	/**
	 * cet exception doit être créer avec un message
	 * @param message
	 */
	public PassThroughInputException(String message) {
		super(message);
	}

}
