package org.imie.todoList;

import java.util.List;

import javax.ejb.Local;

@Local
public interface TestToDoBeanLocal {

	List<ToDoDTO> getAllDTO();

}
