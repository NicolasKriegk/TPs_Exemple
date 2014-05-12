package org.imie.persitanceCommand;

import java.sql.Connection;


public interface IDataBaseSource {

	public abstract Connection getConnection();

}