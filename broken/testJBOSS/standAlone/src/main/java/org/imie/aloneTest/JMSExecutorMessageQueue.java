package org.imie.aloneTest;

import java.util.Properties;

import javax.jms.Connection;
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

public class JMSExecutorMessageQueue {

	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_USERNAME = "imieU";
	private static final String DEFAULT_PASSWORD = "1m1eU";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "remote://localhost:4447";
	private static final String DEFAULT_DESTINATION = "jms/queue/test";

	Context context = null;

	public Connection executeJMS() {
		// Connection cn = null;
		QueueConnection queueConnection = null;
		try {

			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL,
					System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL,
					System.getProperty("username", DEFAULT_USERNAME));
			env.put(Context.SECURITY_CREDENTIALS,
					System.getProperty("password", DEFAULT_PASSWORD));
			context = new InitialContext(env);

			// Perform the JNDI lookups
			String connectionFactoryString = System.getProperty(
					"connection.factory", DEFAULT_CONNECTION_FACTORY);
			// log.info("Attempting to acquire connection factory \"" +
			// connectionFactoryString + "\"");
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context
					.lookup(connectionFactoryString);
			// log.info("Found connection factory \"" + connectionFactoryString
			// + "\" in JNDI");

			// log.info("Attempting to acquire destination \"" +
			// destinationString + "\"");

			// log.info("Found destination \"" + destinationString +
			// "\" in JNDI");

			queueConnection = connectionFactory.createQueueConnection(
					System.getProperty("username", DEFAULT_USERNAME),
					System.getProperty("password", DEFAULT_PASSWORD));

			QueueSession queueSession = queueConnection.createQueueSession(
					false, Session.AUTO_ACKNOWLEDGE);

			String destinationString = System.getProperty("destination",
					DEFAULT_DESTINATION);

			Queue queue = (Queue) context.lookup(destinationString);

			QueueReceiver queueReceiver = queueSession.createReceiver(queue);
			JMSReceveir jmsReceveir = new JMSReceveir();
			queueReceiver.setMessageListener(jmsReceveir);
			QueueSender queueSender = queueSession.createSender(queue);

			queueConnection.start();

			// Close JNDI context
			context.close();
			
			
			for (int i = 0; i < 5; i++) {
				Message message = queueSession.createMessage();
				message.setStringProperty("test", "message de test en mode queue");
				queueSender.send(message);
			}
			Thread.sleep(1000);

		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException e) {
					throw new RuntimeException(e);
				}
			}

			// closing the connection takes care of the session, producer, and
			// consumer
			if (queueConnection != null) {
				try {
					queueConnection.close();
				} catch (JMSException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

}
