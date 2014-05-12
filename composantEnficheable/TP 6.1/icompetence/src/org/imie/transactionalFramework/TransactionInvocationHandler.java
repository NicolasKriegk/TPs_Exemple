package org.imie.transactionalFramework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.imie.DTO.CompetenceDTO;

public class TransactionInvocationHandler implements InvocationHandler {

	private ITransactional iService;
	private Connection connection;
	private ITransactional caller;

	public void setCaller(ITransactional caller) {
		this.caller = caller;
	}

	public TransactionInvocationHandler(ITransactional iService) {
		super();
		this.iService = iService;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object retour = null;

		if (caller != null && caller.getConnection() != null) {
			iService.putInTransaction(caller);
		} else {
			iService.beginTransactionalConnexion();
		}
		retour = method.invoke(iService, args);
		if (caller != null) {
			iService.putOffTransaction();
		} else {
			iService.endTransactionalConnexion();
		}

		return retour;
	};

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
