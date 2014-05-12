package org.imie.JMSService;

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


public class CommunicationService {


	QueueConnection qConn;
	Queue queue;
	
	static CommunicationService communicationService;
	
	public static synchronized CommunicationService getInstance(){
		if (communicationService==null){
			communicationService = new CommunicationService();
		}
		return  communicationService;
	}

	public CommunicationService() {
		super();
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			Context jndiContext = new InitialContext();
			QueueConnectionFactory cFactory = (QueueConnectionFactory) jndiContext
					.lookup("ConnectionFactory");
			qConn = cFactory.createQueueConnection();

			queue = (Queue) jndiContext.lookup("queue/test");
			// Close JNDI context
			jndiContext.close();

			// Start receiving messages
			qConn.start();

			// Start receiving messages
			// qConn.start();
			//
			// qConn.close();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public void initReceveir() {
		try {
			QueueSession qSession = qConn.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			QueueReceiver queueReceiver = qSession.createReceiver(queue);
			IMIEJMSReceiver jmsReceiver = new IMIEJMSReceiver();
			queueReceiver.setMessageListener(jmsReceiver);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

	}

	public void sendJmsMesage(String messageText) {
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
			IMIEJMSReceiver jmsReceveir = new IMIEJMSReceiver();
			queueReceiver.setMessageListener(jmsReceveir);

			// Create Queue Sender
			QueueSender qSender = qSession.createSender(queue);

			// Start receiving messages
			qConn.start();

			// Close JNDI context
			jndiContext.close();

			Message message = qSession.createMessage();
			message.setStringProperty("message", messageText);
			qSender.send(message);


		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} 
	}

	@Override
	protected void finalize() throws Throwable {
		qConn.close();

	}

}
