package tests;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

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

public class DataTestDBunitPostgres extends DBTestCase {
	/** Logger. */
	private static final Logger LOGGER = RootLogger.getLogger(DBTestCase.class);

	/** Driver JDBC. */
	private static final String JDBC_DRIVER = "org.postgresql.Driver";

	/** Base de données HSQLDB nommée "database" qui fonctionne en mode mémoire. */
	private static final String DATABASE = "jdbc:postgresql://localhost:5432/imie";

	/** Utilisateur qui se connecte à la base de données. */
	private static final String USER = "postgres";

	/** GetDataSet mot de passe pour se connecter à la base de données. */
	private static final String PASSWORD = "postgres";

	/** Nom du fichier xml contenant le jeu de données à importer. */
	private static final String INPUT_DATA_SET_FILENAME = "test/tests/inputDataSet.xml";

	/** Nom du fichier xml qui contiendra le jeu de données exporté. */
	private static final String OUTPUT_DATA_SET_FILENAME = "test/tests/outputDataSet.xml";

	/** Nom de la table. */
	public static final String TABLE_NAME = "MANUFACTURER";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	public DataTestDBunitPostgres(String name) {
		super(name);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				JDBC_DRIVER);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				DATABASE);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				USER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				PASSWORD);
	}
	
	   /**
     * Override method to set custom properties/features {@inheritDoc}.
     */
    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        super.setUpDatabaseConfig(config);
        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, new Integer(97));
        config.setFeature(DatabaseConfig.FEATURE_BATCHED_STATEMENTS, true);
    }
    
    /**
     * Méthode étant appelée au début de chaque test.
     */
    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
        return DatabaseOperation.NONE;
    }
    
    /**
     * Méthode étant appelée après à la fin de chaque test.
     */
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

	/**
	 * Charge le jeu de données à partir d'un fichier XML d'import.
	 */
	@Override
	protected IDataSet getDataSet() throws Exception {
		FlatXmlDataSet loadedDataSet = new FlatXmlDataSet(new FileInputStream(
				INPUT_DATA_SET_FILENAME));
		return loadedDataSet;

	}

	@Test
	public void testSelect() {

		TodoDAO dao = new TodoDAO();
		dao = spy(dao);
		when(dao.getDataBaseSource()).thenReturn(
				getMockDataBaseSource());
		List<ToDoBean> beans = dao.selectTodoByLibelle("test");
		Assert.assertEquals(0, beans.size());
	}

	@Test
	public void testInsert() {

		TodoDAO dao = new TodoDAO();
		dao = spy(dao);
		when(dao.getDataBaseSource()).thenReturn(
				getMockDataBaseSource());
		ToDoBean toDoBean = new ToDoBean();
		toDoBean.setTexte("test");
		dao.insertTodo(toDoBean);
		List<ToDoBean> beans = dao.selectTodoByLibelle("test");
		Assert.assertEquals(1, beans.size());
	}

	@Test
	public void testDelete() {

		TodoDAO dao = new TodoDAO();
		dao = spy(dao);
		when(dao.getDataBaseSource()).thenReturn(
				getMockDataBaseSource());
		ToDoBean toDoBean = new ToDoBean();
		toDoBean.setNumero(Integer.valueOf(1));
		toDoBean.setTexte("testDelete");
		List<ToDoBean> beans = dao.selectTodoByLibelle(toDoBean.getTexte());
		if (beans.size() < 1) {
			throw new RuntimeException("pas de record pour delete");
		}
		dao.deleteTodo(toDoBean);
		List<ToDoBean> beans2 = dao.selectTodoByLibelle(toDoBean.getTexte());
		Assert.assertEquals(0, beans2.size());

	}
	
	private IDataBaseSource getMockDataBaseSource() {
		return new DataBaseSourcePostgresPublic();
	}

}
