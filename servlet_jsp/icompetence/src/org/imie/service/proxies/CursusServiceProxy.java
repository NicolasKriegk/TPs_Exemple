package org.imie.service.proxies;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.imie.DTO.CursusDTO;
import org.imie.service.CursusService;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.ICursusService;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public class CursusServiceProxy implements ICursusService {

	private CursusService cursusService;
	
	ITransactional caller=null;

	public CursusServiceProxy(CursusService cursusService,ITransactional caller) {
		super();
		this.cursusService = cursusService;
		this.caller= caller;
	}

	@Override
	public List<CursusDTO> findAll(CursusDTO filtre) throws TransactionalConnectionException,
			ServiceException {
		List<CursusDTO> retour = new ArrayList<CursusDTO>();
		if (caller == null) {
			beginTransactionalConnexion();
		} else {
			putInTransaction(caller);
		}
		retour = cursusService.findAll(filtre);
		if (caller == null) {
			endTransactionalConnexion();
		} else {
			putOffTransaction();
		}
		return retour;
	}

	
	@Override
	public Connection getConnection() {
		return cursusService.getConnection();
	}

	@Override
	public void setConnection(Connection connection) {
		cursusService.setConnection(connection);
		
	}

	@Override
	public void putInTransaction(ITransactional transactional) throws TransactionalConnectionException {
		cursusService.putInTransaction(transactional);
		
	}

	@Override
	public void putOffTransaction() {
		cursusService.putOffTransaction();
		
	}

	@Override
	public void endTransactionalConnexion() {
		cursusService.endTransactionalConnexion();
		
	}

	@Override
	public void beginTransactionalConnexion() throws TransactionalConnectionException {
		cursusService.beginTransactionalConnexion();
		
	}

	
}
