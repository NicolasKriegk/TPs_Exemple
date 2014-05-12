package org.imie.service;

import java.util.List;

import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class UserService extends ATransactional implements IUserService {

	// @Override
	// public List<UserDTO> getUsers(ITransactional transactional) throws
	// TransactionalConnectionException,
	// ServiceException {
	// List<UserDTO> retour = new ArrayList<UserDTO>();
	// if (transactional == null) {
	// beginTransactionalConnexion();
	// } else {
	// putInTransaction(transactional);
	// }
	// retour = getUsers();
	// if (transactional == null) {
	// endTransactionalConnexion();
	// } else {
	// putOffTransaction();
	// }
	// return retour;
	// }

	
	@Override
	public List<UserDTO> getUsers() throws ServiceException, TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO();
		try {
			beginTransactionalConnexion();
			userDAO.putInTransaction(this);
			List<UserDTO> retour = userDAO.getUsers(new UserDTO());
			userDAO.putOffTransaction();
			endTransactionalConnexion();
			return retour;
		} catch (DAOException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new ServiceException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#getUsers()
	 */
	@Override
	public List<UserDTO> getUsers(UserDTO userDTO) throws ServiceException, TransactionalConnectionException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO();
		try {
			beginTransactionalConnexion();
			userDAO.putInTransaction(this);
			List<UserDTO> retour = userDAO.getUsers(userDTO);
			userDAO.putOffTransaction();
			endTransactionalConnexion();
			return retour;
		} catch (DAOException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public UserDTO insertUser(UserDTO userToInsert, ITransactional
	// transactional)
	// throws TransactionalConnectionException, ServiceException {
	// UserDTO retour = null;
	// if (transactional == null) {
	// beginTransactionalConnexion();
	// } else {
	// putInTransaction(transactional);
	// }
	// retour = insertUser(userToInsert);
	// if (transactional == null) {
	// endTransactionalConnexion();
	// } else {
	// putOffTransaction();
	// }
	// return retour;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#insertUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException, ServiceException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO();
		try {
			beginTransactionalConnexion();
			userDAO.putInTransaction(this);
			UserDTO retour = userDAO.insertUser(userToInsert);
			userDAO.putOffTransaction();
			endTransactionalConnexion();
			return retour;
		} catch (DAOException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public UserDTO updateUser(UserDTO userToUpdate, ITransactional
	// transactional)
	// throws TransactionalConnectionException, ServiceException {
	// UserDTO retour = null;
	// if (transactional == null) {
	// beginTransactionalConnexion();
	// } else {
	// putInTransaction(transactional);
	// }
	// retour = updateUser(userToUpdate);
	// if (transactional == null) {
	// endTransactionalConnexion();
	// } else {
	// putOffTransaction();
	// }
	// return retour;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#updateUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException, ServiceException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO();
		try {
			beginTransactionalConnexion();
			userDAO.putInTransaction(this);
			UserDTO retour = userDAO.updateUser(userToUpdate);
			userDAO.putOffTransaction();
			endTransactionalConnexion();
			return retour;
		} catch (DAOException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new ServiceException(e);
		}
	}

	// @Override
	// public void deleteUser(UserDTO userToDelete, ITransactional
	// transactional) throws TransactionalConnectionException,
	// ServiceException {
	//
	// if (transactional == null) {
	// beginTransactionalConnexion();
	// } else {
	// putInTransaction(transactional);
	// }
	// deleteUser(userToDelete);
	// if (transactional == null) {
	// endTransactionalConnexion();
	// } else {
	// putOffTransaction();
	// }
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.service.IUserService#deleteUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException, ServiceException {
		IUserDAO userDAO = BaseConcreteFactory.getInstance().createUserDAO();
		try {
			beginTransactionalConnexion();
			userDAO.putInTransaction(this);
			userDAO.deleteUser(userToDelete);
			userDAO.putOffTransaction();
			endTransactionalConnexion();
		} catch (DAOException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new ServiceException(e);
		}
	}

}
