package org.imie.persitanceCommand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DataBaseCommand implements Command {
	private IDataBaseSource dataBaseConnection;
	private String sqlCommad;
	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private Boolean expectedResult;

	public DataBaseCommand(IDataBaseSource dataBaseConnection,
			String sqlCommad, Boolean expectedResult) {
		super();
		this.dataBaseConnection = dataBaseConnection;
		this.sqlCommad = sqlCommad;
		this.expectedResult = expectedResult;
	}

	@Override
	public Object execute() {
		Object retour = null;
		try {
			initStatement();
			ResultSet rs;
			if (expectedResult) {
				rs = stmt.executeQuery(sqlCommad);
				retour = ResultSetTreatment(rs);
				rs.close();
			} else {
				stmt.execute(sqlCommad);
			}
			closeStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				closeStatement();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return retour;
	}

	private void closeStatement() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	private void initStatement() throws SQLException {
		connection = dataBaseConnection.getConnection();
		stmt = connection.createStatement();
	}

	protected Object ResultSetTreatment(ResultSet resultSet)
			throws SQLException {
		List<Object> retour = new ArrayList<Object>();

		while (resultSet.next()) {
			List<Object> row = new ArrayList<Object>();
			for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
				row.add(resultSet.getObject(i));
			}
			retour.add(row);
		}

		return retour;
	}
}
