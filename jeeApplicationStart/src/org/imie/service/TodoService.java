package org.imie.service;

import java.util.List;

import org.imie.DAO.TodoDAO;
import org.imie.IHMBean.ToDoBean;
import org.imie.JMSService.CommunicationService;

public class TodoService {

	private TodoDAO todoDAO = new TodoDAO();
	private CommunicationService communicationService = CommunicationService.getInstance();

	public List<ToDoBean> selectAllTodo() {

		return todoDAO.selectAllTodo();
		
	}
	
	public void insertTodo(ToDoBean toDoBean) {

		todoDAO.insertTodo(toDoBean);
		communicationService.sendJmsMesage("insert Todo : "+toDoBean.getTexte());
	}

}
