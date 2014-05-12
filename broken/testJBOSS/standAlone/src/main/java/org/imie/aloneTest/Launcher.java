package org.imie.aloneTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.imie.IHMBean.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;
import org.imie.persitanceCommand.DataBaseSourceJNDI;
import org.imie.persitanceCommand.DataBaseSourcePostgresPublic;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JMSExecutorMessage jmsExecutorMessage = new JMSExecutorMessage();
		jmsExecutorMessage.executeJMS();
		JMSExecutorMessageQueue jmsExecutorMessageQueue= new JMSExecutorMessageQueue();
		jmsExecutorMessageQueue.executeJMS();
		
		
		DataBaseCommand dataBaseCommand = new DataBaseCommand(new DataBaseSourcePostgresPublic(),
				"select * from todo", true) {
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
		List<ToDoBean> todos = (List<ToDoBean>) dataBaseCommand.execute();
		for (ToDoBean toDoBean : todos) {
			System.out.println(toDoBean.getTexte());
		}

	}

}
