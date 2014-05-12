package org.imie.persitanceCommand;

import java.sql.Connection;


public interface IDataBaseConnection {

	public abstract Connection getConnection();

}