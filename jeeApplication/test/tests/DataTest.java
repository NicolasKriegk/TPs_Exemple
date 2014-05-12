package tests;

import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.imie.DAO.TodoDAO;
import org.imie.DTO.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;
import org.imie.persitanceCommand.IDataBaseSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
public class DataTest {
	private final IDataBaseSource dataBaseConnection = new DataBaseSourcePostgresPublic();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelect() {

		TodoDAO dao = new TodoDAO();
		dao =spy(dao);
		when(dao.getDataBaseSource()).thenReturn(new DataBaseSourcePostgresPublic());
		List<ToDoBean> beans = dao.selectTodoByLibelle("test");
		Assert.assertEquals(1,beans.size());
	}

	@Test
	public void testInsert() {

		
		TodoDAO dao = new TodoDAO();
		dao =spy(dao);
		when(dao.getDataBaseSource()).thenReturn(new DataBaseSourcePostgresPublic());
		ToDoBean toDoBean = new ToDoBean();
		toDoBean.setTexte("test");
		dao.insertTodo(toDoBean);
		List<ToDoBean> beans = dao.selectTodoByLibelle("test");
		Assert.assertEquals(1,beans.size());
	}

	@Test
	public void testDelete() {
		
		TodoDAO dao = new TodoDAO();
		dao =spy(dao);
		when(dao.getDataBaseSource()).thenReturn(new DataBaseSourcePostgresPublic());
		ToDoBean toDoBean = new ToDoBean();
		toDoBean.setNumero(Integer.valueOf(1));
		toDoBean.setTexte("testDelete");
		List<ToDoBean> beans = dao.selectTodoByLibelle(toDoBean.getTexte());
		if (beans.size()<1){
			throw new RuntimeException("pas de record pour delete");
		}
		dao.deleteTodo(toDoBean);
		List<ToDoBean> beans2 = dao.selectTodoByLibelle(toDoBean.getTexte());
		Assert.assertEquals(0,beans2.size());
		
	}

	
}
