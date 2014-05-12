package org.imie.transactionalFramework;

import java.sql.Connection;


public interface ITransactional {

	public abstract Connection getConnection();

	public abstract void setConnection(Connection connection);

	void putInTransaction(ITransactional transactional) throws TransactionalConnectionException;

	void putOffTransaction();

	void endTransactionalConnexion();

	void beginTransactionalConnexion() throws TransactionalConnectionException;

}