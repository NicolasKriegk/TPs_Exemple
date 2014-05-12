package org.imie.DAO;

import org.imie.persitanceCommand.DataBaseSourceJNDI2;
import org.imie.persitanceCommand.IDataBaseSource;

public abstract class AbstractDAO {
	public IDataBaseSource getDataBaseSource(){
		return new DataBaseSourceJNDI2();
	}
}
