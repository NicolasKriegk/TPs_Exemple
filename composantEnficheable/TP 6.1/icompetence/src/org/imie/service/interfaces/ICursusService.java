package org.imie.service.interfaces;

import java.util.List;

import org.imie.DTO.CursusDTO;
import org.imie.service.exceptions.ServiceException;
import org.imie.transactionalFramework.ITransactional;
import org.imie.transactionalFramework.TransactionalConnectionException;

public interface ICursusService extends ITransactional {

	List<CursusDTO> findAll() throws TransactionalConnectionException, ServiceException;

	List<CursusDTO> findAll(ITransactional transactional) throws TransactionalConnectionException, ServiceException;

	

}