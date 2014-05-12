package org.imie.factory;

import org.imie.DAO.CompetenceDAO;
import org.imie.DAO.CursusDAO;
import org.imie.DAO.UserDAO;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.DAO.proxies.CompetenceDAOProxy;
import org.imie.DAO.proxies.CursusDAOProxy;
import org.imie.DAO.proxies.UserDAOProxy;
import org.imie.service.CursusService;
import org.imie.service.UserService;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.service.proxies.CursusServiceProxy;
import org.imie.service.proxies.UserServiceProxy;
import org.imie.transactionalFramework.ITransactional;

/**
 * factory de base de l'application
 * 
 * @author imie
 * 
 */
public class BaseConcreteFactory implements IFactory {

	/**
	 * attribut stockant l'instance du singleton
	 */
	private static BaseConcreteFactory instance;

	/**
	 * modification de la portée du constructeur pour ne pas pouvoir faire new
	 * BaseFActory hors de la classe
	 */
	private BaseConcreteFactory() {
		super();
	};

	/**
	 * methode qui retourne toujours la même instance de BaseConcreteFactory
	 * @return le singleton instancié
	 */
	public static synchronized BaseConcreteFactory getInstance() {
		if (instance == null) {
			instance = new BaseConcreteFactory();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createUserService()
	 */
	@Override
	public IUserService createUserService() {
		return new UserService();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createCursusSerice()
	 */
	@Override
	public ICursusService createCursusService() {
		return new CursusService();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createUserDAO()
	 */
	@Override
	public IUserDAO createUserDAO() {
		return new UserDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createCursusDAO()
	 */
	@Override
	public ICursusDAO createCursusDAO() {
		return new CursusDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createCompetenceDAO()
	 */
	@Override
	public ICompetenceDAO createCompetenceDAO() {
		return new CompetenceDAO();
	}
}
