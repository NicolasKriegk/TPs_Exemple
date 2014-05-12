package org.imie.todoList;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface TestToDoBeanRemote {
	List<ToDoDTO> getAllDTO();
}
