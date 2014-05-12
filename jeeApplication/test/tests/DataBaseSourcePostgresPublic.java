package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.imie.persitanceCommand.IDataBaseSource;

public class DataBaseSourcePostgresPublic implements IDataBaseSource  {
	
	
	/* (non-Javadoc)
	 * @see org.imie.persitanceCommand.IDataBaseSource#getDataSource()
	 */
	@Override
	public Connection getConnection(){
		Connection cn = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			cn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/imie", "postgres", "postgres"); 
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cn;
	}

}
