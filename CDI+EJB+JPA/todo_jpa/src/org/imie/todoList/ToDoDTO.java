package org.imie.todoList;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="todo")
@Entity
public class ToDoDTO implements Serializable {

	@Column(name="id")
	@Id
	Integer id;
	@Column(name="libelle")
	String label;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	

}
