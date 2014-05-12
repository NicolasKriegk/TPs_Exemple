package org.imie.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.TransactionRequiredException;

import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DAO.interfaces.ICursusDAO;
import org.imie.DAO.interfaces.IUserDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.exeptionManager.ExceptionManager;
import org.imie.factory.BaseConcreteFactory;
import org.imie.transactionalFramework.ATransactional;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * DAO de gestion de la table Utilisateur
 * 
 * @author imie
 * 
 */
public class UserDAO extends ATransactional implements IUserDAO {

	@Override
	public List<UserDTO> getUsers(ITransactional transactional) throws TransactionalConnectionException, DAOException {
		List<UserDTO> retour = new ArrayList<UserDTO>();
		if (transactional == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(transactional);
		}
		retour = getUsers();
		if (transactional == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#getUsers()
	 */
	@Override
	public List<UserDTO> getUsers() throws TransactionalConnectionException, DAOException {

		// initialisation de la liste qui servira au retour
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// création du statement à partir de la connection
			statement = getConnection().createStatement();
			// execution d'une requête SQL et récupération du result dans le
			// resultset
			resultSet = statement.executeQuery("select nom,prenom,datenaiss,id from public.utilisateur");
			// parcours du resultset
			while (resultSet.next()) {
				UserDTO userDTO = buildDTO(resultSet);
				// ajout du DTO dans la liste de retour
				userDTOs.add(userDTO);
			}

		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
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
		return userDTOs;
	}

	/**
	 * construction d'un DTO à partir d'un resultset
	 * 
	 * @param cn
	 *            la connection avec laquel le resulset
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws TransactionalConnectionException
	 * @throws DAOException 
	 * @throws TransactionRequiredException
	 */
	private UserDTO buildDTO(ResultSet resultSet) throws TransactionalConnectionException, DAOException {
		// initialisation du DAO de gestion des compétences
		ICompetenceDAO competenceDAO = BaseConcreteFactory.getInstance().createCompetenceDAO(this);
		ICursusDAO cursusDAO = BaseConcreteFactory.getInstance().createCursusDAO(this);
		// création d'un nouveau UserDTO
		UserDTO userDTO = new UserDTO();
		// affectation des attribut du UserDTO à partir des valeurs du
		// resultset sur l'enregistrement courant
		try {
			userDTO.setNom(resultSet.getString("nom"));
			userDTO.setPrenom(resultSet.getString("prenom"));
			userDTO.setId(resultSet.getInt("id"));
			userDTO.setDateNaiss(resultSet.getDate("datenaiss"));
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		

		// récupération des compétences du user grâce au competenceDAO
		// la connection passée en paramètre permet de partager la
		// connection entre cette methode et celle appelée

		List<CompetenceDTO> competenceDTOs = competenceDAO.getCompetenceByUser(userDTO);
		// parcours des compétences du user
		for (CompetenceDTO competenceDTO : competenceDTOs) {
			// ajout des compétences sur le UserDTO à partir de celles
			// fournies par le CompetenceDAO
			userDTO.addCompetence(competenceDTO);
		}

		CursusDTO cursusDTO = cursusDAO.findByUser(userDTO);

		userDTO.setCursus(cursusDTO);
		return userDTO;
	}

	@Override
	public UserDTO insertUser(UserDTO userToInsert,ITransactional transactional) throws TransactionalConnectionException, DAOException {
		UserDTO retour = null;
		if (transactional == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(transactional);
		}
		retour = insertUser(userToInsert);
		if (transactional == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#insertUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO insertUser(UserDTO userToInsert) throws TransactionalConnectionException, DAOException {
		UserDTO userDTORetour = null;
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		UserDTO userDTOinserted = null;
		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset
			String insertInstruction = "insert into utilisateur (nom,prenom,datenaiss,id_cursus) values (?,?,?,?) returning nom,prenom,datenaiss,id";
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, userToInsert.getNom());
			preparedStatement.setString(2, userToInsert.getPrenom());
			preparedStatement.setDate(3, new java.sql.Date(userToInsert.getDateNaiss().getTime()));
			preparedStatement.setInt(4, userToInsert.getCursus().getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userDTOinserted = buildDTO(resultSet);
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
		return userDTOinserted;
	}

	@Override
	public UserDTO updateUser(UserDTO userToUpdate,ITransactional transactional) throws TransactionalConnectionException, DAOException {

		UserDTO retour = null;
		if (transactional == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(transactional);
		}
		retour = updateUser(userToUpdate);
		if (transactional == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#updateUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public UserDTO updateUser(UserDTO userToUpdate) throws TransactionalConnectionException, DAOException {
		UserDTO userDTORetour = null;
		// chargement du driver postgres dans la JVM

		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {
			// execution d'une requête SQL et récupération du result dans le
			// resultset
			String insertInstruction = "update utilisateur set nom=?, prenom=?, datenaiss=?, id_cursus=? where id=?";
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setString(1, userToUpdate.getNom());
			preparedStatement.setString(2, userToUpdate.getPrenom());
			if (userToUpdate.getDateNaiss()!=null){
				preparedStatement.setDate(3, new java.sql.Date(userToUpdate.getDateNaiss().getTime()));
			}else{
				preparedStatement.setNull(3, Types.DATE);
			}

			if (userToUpdate.getCursus() != null) {
				preparedStatement.setInt(4, userToUpdate.getCursus().getId());
			} else {
				preparedStatement.setNull(4, Types.INTEGER);
			}

			preparedStatement.setInt(5, userToUpdate.getId());
			preparedStatement.executeUpdate();

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
		return userDTORetour;
	}

	
	@Override
	public void deleteUser(UserDTO userToDelete,ITransactional transactional) throws TransactionalConnectionException, DAOException {
	
		if (transactional == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(transactional);
		}
		deleteUser(userToDelete);
		if (transactional == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IuserDAO#deleteUser(org.imie.DTO.UserDTO)
	 */
	@Override
	public void deleteUser(UserDTO userToDelete) throws TransactionalConnectionException, DAOException {
		
		// déclaration de la variable de statement
		Statement statement = null;
		// déclaration de la variable de resultset
		ResultSet resultSet = null;
		try {

			// execution d'une requête SQL et récupération du result dans le
			// resultset
			String insertInstruction = "delete from utilisateur where id=?";
			PreparedStatement preparedStatement = getConnection().prepareStatement(insertInstruction);
			preparedStatement.setInt(1, userToDelete.getId());
			preparedStatement.executeUpdate();

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
	}
}
