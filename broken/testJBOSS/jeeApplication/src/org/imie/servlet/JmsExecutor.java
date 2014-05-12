package org.imie.servlet;

import java.io.IOException;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class jmsExecutor
 */
@WebServlet("/jmsExecutor")
public class JmsExecutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JmsExecutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Hashtable<String, String> env = new Hashtable<String, String>();
			// env.put(Context.INITIAL_CONTEXT_FACTORY,
			// "org.jboss.naming.remote.client.InitialContextFactory");
			// env.put(Context.PROVIDER_URL, "remote://localhost:4447");
			Context jndiContext = new InitialContext();
			// Look up our data source
			// DataSource ds = (DataSource)
			// jndiContext.lookup("pgsqlDataSource");
			QueueConnectionFactory cFactory = (QueueConnectionFactory) jndiContext
					.lookup("ConnectionFactory");
			QueueConnection qConn = cFactory.createQueueConnection();
			QueueSession qSession = qConn.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) jndiContext.lookup("queue/test");

			QueueReceiver queueReceiver = qSession.createReceiver(queue);
			JMSReceveir jmsReceveir = new JMSReceveir();
			queueReceiver.setMessageListener(jmsReceveir);

			// Create Queue Sender
			QueueSender qSender = qSession.createSender(queue);

			// Start receiving messages
			qConn.start();

			// Close JNDI context
			jndiContext.close();

			Message message = qSession.createMessage();
			message.setStringProperty("test", "message de test en mode queue");
			qSender.send(message);
			Thread.sleep(10000);
			qConn.close();

		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
