package org.imie.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * DAO de la table cursus
 * @author imie
 *
 */
public class CursusDAO extends ATransactional implements ICursusDAO {
//	@Override
//	public CursusDTO findByUser(UserDTO userDTO,ITransactional transactional) throws TransactionalConnectionException, DAOException {
//		CursusDTO retour = null;
//		if (transactional == null) {
//			beginTransactionalConnexion();
//		} else {
//			putInTransaction(transactional);
//		}
//		findByUser(userDTO);
//		if (transactional == null) {
//			endTransactionalConnexion();
//		} else {
//			putOffTransaction();
//		}
//		return retour;
//	}	
	/* (non-Javadoc)
	 * @see org.imie.DAO.IUserDAO#findByUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public CursusDTO findByUser(UserDTO userDTO) throws TransactionalConnectionException, DAOException {

		// initialisation de la liste qui servira au retour
		CursusDTO cursusDTO = null;
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// création du statement à partir de la connection
			String selectInstruction = "select cursus.id,cursus.libelle from cursus join utilisateur on cursus.id=utilisateur.id_cursus where utilisateur.id=?";
			PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
			preparedStatement.setInt(1, userDTO.getId());
			resultSet = preparedStatement.executeQuery();
			// parcours du resultset

			if (resultSet.next()) {
				cursusDTO = new CursusDTO();
				// affectation des attribut du UserDTO à partir des valeurs du
				// resultset sur l'enregistrement courant
				cursusDTO.setLibelle(resultSet.getString("libelle"));
				cursusDTO.setId(resultSet.getInt("id"));
				// ajout du DTO dans la liste de retour

			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new DAOException(e);
		} finally {
			// libération des ressources
			
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				ExceptionManager.getInstance().manageException(e);
			}
		}
		return cursusDTO;
	}
	
//	@Override
//	public List<CursusDTO> findAll(ITransactional transactional) throws TransactionalConnectionException, DAOException {
//		List<CursusDTO> retour = new ArrayList<CursusDTO>();
//		if (transactional == null) {
//			beginTransactionalConnexion();
//		} else {
//			putInTransaction(transactional);
//		}
//		retour = findAll();
//		if (transactional == null) {
//			endTransactionalConnexion();
//		} else {
//			putOffTransaction();
//		}
//		return retour;
//	}

	/* (non-Javadoc)
	 * @see org.imie.DAO.IUserDAO#findAll()
	 */
	@Override
	public List<CursusDTO> findAll(CursusDTO filtre) throws TransactionalConnectionException, DAOException {

		String criteriaSQL = "";
		Boolean isfirstCriteria = true;
		if (filtre.getId()!=null){
			criteriaSQL+=(isfirstCriteria?" where ":" and ")+"id="+filtre.getId();
			isfirstCriteria=false;
		}
		if (filtre.getLibelle()!=null){
			criteriaSQL+=(isfirstCriteria?" where ":" and ")+"libelle='"+filtre.getLibelle()+"'";
			isfirstCriteria=false;
		}
		
		
		
		// initialisation de la liste qui servira au retour
		List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// création du statement à partir de la connection
			String selectInstruction = "select id,libelle from cursus"+criteriaSQL;
			PreparedStatement preparedStatement = getConnection().prepareStatement(selectInstruction);
			resultSet = preparedStatement.executeQuery();
			// parcours du resultset
			while (resultSet.next()) {
				CursusDTO cursusDTO = new CursusDTO();
				// affectation des attribut du UserDTO à partir des valeurs du
				// resultset sur l'enregistrement courant
				cursusDTO.setLibelle(resultSet.getString("libelle"));
				cursusDTO.setId(resultSet.getInt("id"));
				// ajout du DTO dans la liste de retour
				cursusDTOs.add(cursusDTO);
			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
			throw new DAOException(e);
		} finally {
			
			// libération des ressources
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e) {
				ExceptionManager.getInstance().manageException(e);
			}
		}
		return cursusDTOs;
	}
}
