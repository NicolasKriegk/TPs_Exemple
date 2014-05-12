package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DAO.exceptions.DAOException;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * interface de DAO de gestion des utlisateurs
 * 
 * @author imie
 * 
 */
public interface IUserDAO extends ITransactional {

	/**
	 * rechercher tous les utlisateurs
	 * 
	 * @return liste des users avec alimentation des compétences sans filtre
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 */
	public abstract List<UserDTO> getUsers(UserDTO userDTO) throws TransactionalConnectionException, DAOException;

	/**
	 * insérer un utilisateur
	 * 
	 * @param userToInsert
	 * @return l'utilisateur indéré
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 */
	public abstract UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException, DAOException;

	/**
	 * mettre à jour un utlisateur
	 * 
	 * @param userToUpdate
	 * @return l'utilisateur mis à jour
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 */
	public abstract UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException, DAOException;

	/**
	 * supprimer un utilisateur
	 * 
	 * @param userToDelete
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 */
	public abstract void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException, DAOException;
//
//	public abstract void deleteUser(UserDTO userToDelete, ITransactional transactional) throws TransactionalConnectionException,
//			DAOException;

//	UserDTO updateUser(UserDTO userToUpdate, ITransactional transactional) throws TransactionalConnectionException,
//			DAOException;
//
//	UserDTO insertUser(UserDTO userToInsert, ITransactional transactional) throws TransactionalConnectionException,
//			DAOException;
//
//	List<UserDTO> getUsers(ITransactional transactional) throws TransactionalConnectionException, DAOException;

}