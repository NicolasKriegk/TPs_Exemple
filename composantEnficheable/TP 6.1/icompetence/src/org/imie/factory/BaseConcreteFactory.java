package org.imie.factory;

import org.imie.DAO.CompetenceDAO;
import org.imie.DAO.CursusDAO;
import org.imie.DAO.UserDAO;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.service.CursusService;
import org.imie.service.UserService;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalFactory;

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
	public IUserService createUserService(ITransactional caller) {
		TransactionalFactory<UserService> fact = (TransactionalFactory<UserService>) TransactionalFactory.getInstance();
		return fact.createTransactionalService(new UserService(), caller);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createCursusSerice()
	 */
	@Override
	public ICursusService createCursusService(ITransactional caller) {
		TransactionalFactory<CursusService> fact = (TransactionalFactory<CursusService>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new CursusService(), caller);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createUserDAO()
	 */
	@Override
	public IUserDAO createUserDAO(ITransactional caller) {
		TransactionalFactory<UserDAO> fact = (TransactionalFactory<UserDAO>) TransactionalFactory.getInstance();
		return fact.createTransactionalService(new UserDAO(), caller);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createCursusDAO()
	 */
	@Override
	public ICursusDAO createCursusDAO(ITransactional caller) {
		TransactionalFactory<CursusDAO> fact = (TransactionalFactory<CursusDAO>) TransactionalFactory.getInstance();
		return fact.createTransactionalService(new CursusDAO(), caller);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.factory.IFactory#createCompetenceDAO()
	 */
	@Override
	public ICompetenceDAO createCompetenceDAO(ITransactional caller) {
		TransactionalFactory<CompetenceDAO> fact = (TransactionalFactory<CompetenceDAO>) TransactionalFactory
				.getInstance();
		return fact.createTransactionalService(new CompetenceDAO(), caller);
	}
}
