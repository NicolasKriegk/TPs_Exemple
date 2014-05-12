package org.imie.DAO.proxies;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.CompetenceDAO;
import org.imie.DAO.exceptions.DAOException;
import org.imie.DAO.interfaces.ICompetenceDAO;
import org.imie.DTO.CompetenceDTO;
import org.imie.DTO.UserDTO;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class CompetenceDAOProxy implements ICompetenceDAO {

	CompetenceDAO competenceDAO;
	ITransactional caller =null;
	
	public CompetenceDAOProxy(CompetenceDAO competenceDAO,ITransactional caller) {
		super();
		this.competenceDAO = competenceDAO;
		this.caller=caller;
	}
	
//	@Override
//	public List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO) throws TransactionalConnectionException,
//			DAOException {
//		return getCompetenceByUser(userDTO, null);
//	}


	@Override
	public List<CompetenceDTO> getCompetenceByUser(UserDTO userDTO)
			throws TransactionalConnectionException, DAOException {
		List<CompetenceDTO> retour = new ArrayList<CompetenceDTO>();
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = competenceDAO.getCompetenceByUser(userDTO);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}
	@Override
	public Connection getConnection() {
		return competenceDAO.getConnection();
	}

	@Override
	public void setConnection(Connection connection) {
		competenceDAO.setConnection(connection);
		
	}

	@Override
	public void putInTransaction(ITransactional transactional) throws TransactionalConnectionException {
		competenceDAO.putInTransaction(transactional);
		
	}

	@Override
	public void putOffTransaction() {
		competenceDAO.putOffTransaction();
		
	}

	@Override
	public void endTransactionalConnexion() {
		competenceDAO.endTransactionalConnexion();
		
	}

	@Override
	public void beginTransactionalConnexion() throws TransactionalConnectionException {
		competenceDAO.beginTransactionalConnexion();
		
	}

}
