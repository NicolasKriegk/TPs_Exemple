package org.imie.DAO.proxies;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.UserDAO;
import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class UserDAOProxy implements IUserDAO {

	UserDAO userDAO;
	
	ITransactional  caller = null;
			
	public UserDAOProxy(UserDAO userDAO,ITransactional  caller) {
		super();
		this.caller = caller;
		this.userDAO = userDAO;
	}
	
	
	@Override
	public Connection getConnection() {
		return userDAO.getConnection();
	}

	@Override
	public void setConnection(Connection connection) {
		userDAO.setConnection(connection);
		
	}

	@Override
	public void putInTransaction(ITransactional transactional) throws TransactionalConnectionException {
		userDAO.putInTransaction(transactional);
		
	}

	@Override
	public void putOffTransaction() {
		userDAO.putOffTransaction();
		
	}

	@Override
	public void endTransactionalConnexion() {
		userDAO.endTransactionalConnexion();
		
	}

	@Override
	public void beginTransactionalConnexion() throws TransactionalConnectionException {
		userDAO.beginTransactionalConnexion();
		
	}

//	@Override
//	public List<UserDTO> getUsers() throws TransactionalConnectionException, DAOException {
//		return getUsers(null);
//	}
//
//
//	@Override
//	public UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException, DAOException {
//		return insertUser(userToInsert,null);
//	}
//
//	@Override
//	public UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException, DAOException {
//		return updateUser(userToUpdate,null);
//	}
//
//
//	@Override
//	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException, DAOException {
//		deleteUser(userToDelete,null);
//		
//	}


	@Override
	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException,
			DAOException {
		
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		userDAO.deleteUser(userToDelete);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
	}


	@Override
	public UserDTO updateUser(UserDTO userToUpdate)
			throws TransactionalConnectionException, DAOException {
		UserDTO retour = null;
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userDAO.updateUser(userToUpdate);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}


	@Override
	public UserDTO insertUser(UserDTO userToInsert)
			throws TransactionalConnectionException, DAOException {
		UserDTO retour = null;
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userDAO.insertUser(userToInsert);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}


	@Override
	public List<UserDTO> getUsers(UserDTO userDTO) throws TransactionalConnectionException, DAOException {
		List<UserDTO> retour = new ArrayList<UserDTO>();
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userDAO.getUsers(userDTO);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}




}
