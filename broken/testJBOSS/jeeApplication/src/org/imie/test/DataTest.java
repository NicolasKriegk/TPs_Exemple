package org.imie.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import junit.framework.Assert;

import org.imie.IHMBean.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;
import org.imie.persitanceCommand.DataBaseSourcePostgresPublic;
import org.imie.persitanceCommand.IDataBaseConnection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataTest {
	private final IDataBaseConnection dataBaseConnection = new DataBaseSourcePostgresPublic();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
		// System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jboss.as.naming.InitialContextFactory");
		// Assert.assertTrue(_initialContext.lookup("java:/ConnectionFactory")
		// instanceof javax.jms.ConnectionFactory);
		//
		// Properties env = new Properties();
		// env.setProperty(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");
		// env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
		// env.setProperty(Context.PROVIDER_URL, "localhost:1099");
		//
		// try {
		// InitialContext context = new InitialContext(env);
		// HelloWorldRemote helloWorldRemote = (HelloWorldRemote)
		// context.lookup("HelloWorld/remote");
		// helloWorldRemote.sayHello("Guest");
		// } catch (NamingException e) {
		// e.printStackTrace();
		// }
//		Context ctx = null;
//		Hashtable ht = new Hashtable();
//		ht.put(Context.INITIAL_CONTEXT_FACTORY,
//				"org.jboss.as.naming.InitialContextFactory");
//		ht.put(Context.PROVIDER_URL, "jdbc:postgresql://localhost:5432/imie");
//		ht.put(Context.SECURITY_PRINCIPAL, "postgres");
//		ht.put(Context.SECURITY_CREDENTIALS, "postgres");
//		try {
//			ctx = new InitialContext(ht);
//
//			Context envCtx = (Context) ctx.lookup("java:jboss");
//
//			// Look up our data source
//			DataSource ds = (DataSource) envCtx.lookup("pgsqlDataSource");
//
//			Connection con = ds.getConnection();
//
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup
		// ("<your-datasource-jndi-name>");
		// conn = ds.getConnection();
		DataBaseCommand dataBaseCommand = new DataBaseCommand(dataBaseConnection,
				"insert into todo(libelle) values ('test')", false) {

		};
		dataBaseCommand.execute();
		
		
		
		DataBaseCommand dataBaseCommand2 = new DataBaseCommand(dataBaseConnection,
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
		
		@SuppressWarnings("unchecked")
		List<ToDoBean> beans = (List<ToDoBean>) dataBaseCommand2.execute();
		ToDoBean last = beans.get(beans.size()-1);
		Assert.assertEquals(last.getTexte(),"test");
		
		DataBaseCommand dataBaseCommand3 = new DataBaseCommand(dataBaseConnection,
				"delete from todo where libelle='test'", false) {

		};
		
		dataBaseCommand3.execute();
		
		
		
	}

}
