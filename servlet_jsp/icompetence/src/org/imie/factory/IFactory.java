package org.imie.factory;

import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ITransactional;

/**
 * Interface de la factory de l'application
 * @author imie
 *
 */
public interface IFactory {
	/**
	 * créer un service transactionel (un proxy de service) gérant les Users
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract IUserService createUserService();

	/**
	 * créer un service transactionel (un proxy de service) gérant les cursus
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract ICursusService createCursusService();

	/**
	 * créer un dao transactionel dédié à la table utilisateur (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract IUserDAO createUserDAO();

	/**
	 * créer un dao transactionel dédié à la table cursus (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract ICursusDAO createCursusDAO();

	/**
	 * créer un dao transactionel dédié à la table competence (un proxy de dao)
	 * 
	 * @param caller
	 *            objet transactionel appelant ce service. null si début de
	 *            transaction
	 * @return
	 */
	public abstract ICompetenceDAO createCompetenceDAO();

}