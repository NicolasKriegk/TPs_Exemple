package org.imie.transactionalFramework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.transaction.TransactionRequiredException;

import org.imie.exeptionManager.ExceptionManager;

public abstract class ATransactional implements ITransactional {

	private Connection connection;

	private Boolean transactionalInitiator = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.connectionFramework.IService#getConnection()
	 */
	@Override
	public Connection getConnection() {
		return connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.connectionFramework.IService#setConnection(java.sql.Connection)
	 */
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IAbstractDAO#putInTransaction(org.imie.DAO.AbstractDAO)
	 */
	@Override
	public void putInTransaction(ITransactional transactional) throws TransactionalConnectionException {
		if (transactionalInitiator != null) {
			throw new TransactionalConnectionException(
					"une connection est déjà en cours. impossible de s'inscrire dans une autre transaction");
		}
		this.connection = transactional.getConnection();
		this.transactionalInitiator = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IAbstractDAO#putOffTransaction()
	 */
	@Override
	public void putOffTransaction() {
		this.connection = null;
		this.transactionalInitiator = null;
	}

	@Override
	public void beginTransactionalConnexion() throws TransactionalConnectionException {
		System.out.println("$$$$$$$$$$$$ debut transaction " + this.getClass().getName());
		if (transactionalInitiator != null) {
			if (transactionalInitiator == true) {
				throw new TransactionalConnectionException("en cours de connexion");
			} else if (transactionalInitiator == false) {
				System.out.println("$$$$$$$$$$$$ transaction exclave " + this.getClass().getName());
				// rien à faire
			}
		} else {
			System.out.println("$$$$$$$$$$$$ creation transaction");
			transactionalInitiator = true;
			initConnection();
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				ExceptionManager.getInstance().manageException(e);
			}
		}
	}

	@Override
	public void endTransactionalConnexion() {
		System.out.println("$$$$$$$$$$$$ fin transaction " + this.getClass().getName());
		if (transactionalInitiator == true) {
			// libération des ressources
			try {
				if (connection != null) {
					System.out.println("$$$$$$$$$$$$ commit " + this.getClass().getName());
					connection.commit();
				}
			} catch (SQLException e) {
				if (connection != null) {
					try {
						System.out.println("$$$$$$$$$$$$ rollback " + this.getClass().getName());
						connection.rollback();
					} catch (SQLException e1) {
						ExceptionManager.getInstance().manageException(e1);
					}
				}
				ExceptionManager.getInstance().manageException(e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						ExceptionManager.getInstance().manageException(e);
					}
				}
				transactionalInitiator = null;
			}

		}
	}

	private void initConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imie", "postgres", "postgres");
		} catch (SQLException e) {
			ExceptionManager.getInstance().manageException(e);
		} catch (ClassNotFoundException e) {
			ExceptionManager.getInstance().manageException(e);
		}
	}

}
