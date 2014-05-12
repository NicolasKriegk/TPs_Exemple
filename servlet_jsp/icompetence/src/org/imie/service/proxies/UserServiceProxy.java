package org.imie.service.proxies;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.imie.DTO.UserDTO;
import org.imie.service.UserService;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class UserServiceProxy implements IUserService {
	private UserService userService;

	ITransactional caller=null;
	
	public UserServiceProxy(UserService userService,ITransactional caller) {
		super();
		this.userService = userService;
		this.caller= caller;
	}


	@Override
	public Connection getConnection() {
		return userService.getConnection();
	}

	@Override
	public void setConnection(Connection connection) {
		userService.setConnection(connection);
		
	}

	@Override
	public void putInTransaction(ITransactional transactional) throws TransactionalConnectionException {
		userService.putInTransaction(transactional);
		
	}

	@Override
	public void putOffTransaction() {
		userService.putOffTransaction();
		
	}

	@Override
	public void endTransactionalConnexion() {
		userService.endTransactionalConnexion();
		
	}

	@Override
	public void beginTransactionalConnexion() throws TransactionalConnectionException {
		userService.beginTransactionalConnexion();
		
	}


	@Override
	public UserDTO insertUser(UserDTO userToInsert)
			throws TransactionalConnectionException, ServiceException {
		UserDTO retour = null;
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userService.insertUser(userToInsert);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}


	@Override
	public List<UserDTO> getUsers() throws TransactionalConnectionException,
			ServiceException {
		List<UserDTO> retour = new ArrayList<UserDTO>();
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userService.getUsers();
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}


	@Override
	public UserDTO updateUser(UserDTO userToUpdate)
			throws TransactionalConnectionException, ServiceException {
		UserDTO retour = null;
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userService.updateUser(userToUpdate);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}


	@Override
	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException,
			ServiceException {

		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		userService.updateUser(userToDelete);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}

		
	}


	@Override
	public List<UserDTO> getUsers(UserDTO userDTO) throws ServiceException, TransactionalConnectionException {
		List<UserDTO> retour = new ArrayList<UserDTO>();
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = userService.getUsers(userDTO);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}
}
