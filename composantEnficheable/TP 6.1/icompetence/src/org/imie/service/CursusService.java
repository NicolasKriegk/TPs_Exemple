package org.imie.service;

import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.CursusDAO;
import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.ICursusService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class CursusService extends ATransactional implements ICursusService {
	@Override
	public List<CursusDTO> findAll(ITransactional transactional) throws TransactionalConnectionException, ServiceException{
		List<CursusDTO> retour = new ArrayList<CursusDTO>();
		if (transactional == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(transactional);
		}
		retour = findAll();
		if (transactional == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}
	@Override
	public List<CursusDTO> findAll() throws TransactionalConnectionException, ServiceException{
		ICursusDAO cursusDAO = BaseConcreteFactory.getInstance().createCursusDAO(this);
		try {
			return cursusDAO.findAll();
		} catch (DAOException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new ServiceException(e);
		}
	}
}
