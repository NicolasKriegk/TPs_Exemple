package org.imie.DAO.proxies;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.CursusDAO;
import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class CursusDAOProxy implements ICursusDAO {

	CursusDAO cursusDAO;
	ITransactional caller =null;
	public CursusDAOProxy(CursusDAO cursusDAO,ITransactional caller) {
		super();
		this.caller = caller;
		this.cursusDAO = cursusDAO;
	}

	
	@Override
	public Connection getConnection() {
		return cursusDAO.getConnection();
	}

	@Override
	public void setConnection(Connection connection) {
		cursusDAO.setConnection(connection);
		
	}

	@Override
	public void putInTransaction(ITransactional transactional) throws TransactionalConnectionException {
		cursusDAO.putInTransaction(transactional);
		
	}

	@Override
	public void putOffTransaction() {
		cursusDAO.putOffTransaction();
		
	}

	@Override
	public void endTransactionalConnexion() {
		cursusDAO.endTransactionalConnexion();
		
	}

	@Override
	public void beginTransactionalConnexion() throws TransactionalConnectionException {
		cursusDAO.beginTransactionalConnexion();
		
	}


//
//
//
//
//	@Override
//	public CursusDTO findByUser(UserDTO userDTO) throws TransactionalConnectionException, DAOException {
//		return findByUser(userDTO,null);
//	}
//
//
//
//
//
//
//	@Override
//	public List<CursusDTO> findAll() throws TransactionalConnectionException, DAOException {
//		return findAll(null);
//	}

	@Override
	public CursusDTO findByUser(UserDTO userDTO) throws TransactionalConnectionException,
			DAOException {
		CursusDTO retour = null;
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = cursusDAO.findByUser(userDTO);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}

	@Override
	public List<CursusDTO> findAll(CursusDTO filtre) throws TransactionalConnectionException, DAOException {
		List<CursusDTO> retour = new ArrayList<CursusDTO>();
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = cursusDAO.findAll(filtre);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}

}
