package org.imie.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.imie.IHMBean.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;
import org.imie.persitanceCommand.DataBaseSourceJNDI;
import org.imie.persitanceCommand.IDataBaseConnection;

public class TodoDAO {

	private final IDataBaseConnection dataBaseConnection = new DataBaseSourceJNDI();

	/**
	 * @return
	 */
	public List<ToDoBean> selectAllTodo() {
		DataBaseCommand dataBaseCommand = buildSelectCommand();

		List<ToDoBean> todos = new ArrayList<ToDoBean>();
		todos = castCommandResult(dataBaseCommand);
		return todos;
	}

	private DataBaseCommand buildSelectCommand() {
		DataBaseCommand dataBaseCommand = new DataBaseCommand(
				dataBaseConnection, "select * from todo", true) {
			@Override
			protected Object ResultSetTreatment(ResultSet resultSet)
					throws SQLException {
				List<ToDoBean> todos = new ArrayList<ToDoBean>();
				while (resultSet.next()) {
					ToDoBean toDoBean = new ToDoBean();
					toDoBean.setNumero(resultSet.getDouble("id"));
					toDoBean.setTexte(resultSet.getString("libelle"));
					todos.add(toDoBean);
				}
				return todos;
			}
		};
		return dataBaseCommand;
	}

	public void insertTodo(ToDoBean toDoBean) {

		DataBaseCommand dataBaseCommand = new DataBaseCommand(
				dataBaseConnection, "insert into todo(libelle) values ('"
						+ toDoBean.getTexte() + "')", false) {

		};
		dataBaseCommand.execute();
	}

	@SuppressWarnings("unchecked")
	private List<ToDoBean> castCommandResult(DataBaseCommand dataBaseCommand) {
		return (List<ToDoBean>) dataBaseCommand.execute();
	}

}
