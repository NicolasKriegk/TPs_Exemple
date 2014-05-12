package org.imie.DAO.interfaces;

import java.util.List;

import org.imie.DAO.exceptions.DAOException;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;
/**
 * interface de DAO de la table cursus
 * @author imie
 *
 */
public interface ICursusDAO extends ITransactional{

	/**
	 * rechercher le cursus d'un utilisateur
	 * @param userDTO
	 * @return
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 */
	public abstract CursusDTO findByUser(UserDTO userDTO) throws 
			TransactionalConnectionException, DAOException;

	/**
	 * recherche de tous les cursus
	 * @return
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 */
	public abstract List<CursusDTO> findAll() throws TransactionalConnectionException, DAOException;

	CursusDTO findByUser(UserDTO userDTO, ITransactional transactional) throws TransactionalConnectionException, DAOException;

	List<CursusDTO> findAll(ITransactional transactional) throws TransactionalConnectionException, DAOException;

}