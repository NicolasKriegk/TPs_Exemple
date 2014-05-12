package org.imie.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.imie.DTO.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;

public class TodoDAO extends AbstractDAO {


	/**
	 * @return
	 */
	public List<ToDoBean> selectAllTodo() {
		DataBaseCommand dataBaseCommand = buildSelectCommand("");

		List<ToDoBean> todos = new ArrayList<ToDoBean>();
		todos = castCommandResult(dataBaseCommand);
		return todos;
	}
	
	public List<ToDoBean> selectTodoByLibelle(String libelle) {
		DataBaseCommand dataBaseCommand = buildSelectCommand("where libelle='"+libelle+"'");

		List<ToDoBean> todos = new ArrayList<ToDoBean>();
		todos = castCommandResult(dataBaseCommand);
		return todos;
	}

	private DataBaseCommand buildSelectCommand(String whereExpression) {
		DataBaseCommand dataBaseCommand = new DataBaseCommand(
				getDataBaseSource(), "select * from todo"
						+ (whereExpression.length() > 0 ? " " : "")
						+ whereExpression, true) {
			@Override
			protected Object ResultSetTreatment(ResultSet resultSet)
					throws SQLException {
				List<ToDoBean> todos = new ArrayList<ToDoBean>();
				while (resultSet.next()) {
					ToDoBean toDoBean = new ToDoBean();
					toDoBean.setNumero(resultSet.getInt("id"));
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
				getDataBaseSource(), "insert into todo(libelle) values ('"
						+ toDoBean.getTexte() + "')", false) {

		};
		dataBaseCommand.execute();
	}
	
	public void deleteTodo(ToDoBean toDoBean) {

		DataBaseCommand dataBaseCommand = new DataBaseCommand(
				getDataBaseSource(), "delete from todo where id='"+toDoBean.getNumero()+"'", false) {

		};
		dataBaseCommand.execute();
	}

	@SuppressWarnings("unchecked")
	private List<ToDoBean> castCommandResult(DataBaseCommand dataBaseCommand) {
		return (List<ToDoBean>) dataBaseCommand.execute();
	}
	
	

}
