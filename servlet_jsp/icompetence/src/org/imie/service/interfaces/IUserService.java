package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.UserDTO;
import org.imie.service.exceptions.ServiceException;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface IUserService extends ITransactional {

	/**
	 * @return liste des users avec alimentation des compétences sans filtre
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException 
	 * @throws ServiceException 
	 */
	public abstract List<UserDTO> getUsers() throws TransactionalConnectionException, ServiceException;

	public abstract UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException, ServiceException;

	public abstract UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException, ServiceException;

	public abstract void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException, ServiceException;

	List<UserDTO> getUsers(UserDTO userDTO) throws ServiceException, TransactionalConnectionException;


}