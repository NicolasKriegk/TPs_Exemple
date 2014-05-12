package org.imie.persitanceCommand;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseSourceJNDI implements IDataBaseSource {
	
	
	/* (non-Javadoc)
	 * @see org.imie.persitanceCommand.IDataBaseSource#getDataSource()
	 */
	@Override
	public Connection getConnection(){
		Connection cn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:jboss");

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("pgsqlDataSource");
			cn = ds.getConnection();
			
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cn;
	}

}
