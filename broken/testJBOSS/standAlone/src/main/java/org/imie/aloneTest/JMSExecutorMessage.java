package org.imie.aloneTest;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSExecutorMessage {

	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_USERNAME = "imieU";
	private static final String DEFAULT_PASSWORD = "1m1eU";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "remote://localhost:4447";
	private static final String DEFAULT_DESTINATION = "jms/queue/test";

	Context context = null;

	public Connection executeJMS() {
		// Connection cn = null;
		Connection connection = null;
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
			ConnectionFactory connectionFactory = (ConnectionFactory) context
					.lookup(connectionFactoryString);
			// log.info("Found connection factory \"" + connectionFactoryString
			// + "\" in JNDI");

			String destinationString = System.getProperty("destination",
					DEFAULT_DESTINATION);
			// log.info("Attempting to acquire destination \"" +
			// destinationString + "\"");
			Destination destination = (Destination) context
					.lookup(destinationString);
			// log.info("Found destination \"" + destinationString +
			// "\" in JNDI");

			// Create the JMS connection, session, producer, and consumer
			connection = connectionFactory.createConnection(
					System.getProperty("username", DEFAULT_USERNAME),
					System.getProperty("password", DEFAULT_PASSWORD));

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			MessageConsumer consumer = session.createConsumer(destination);

			// Close JNDI context
			context.close();

			connection.start();


			// log.info("Sending " + count + " messages with content: " +
			// content);

			// Send the specified number of messages
			for (int i = 0; i < 5; i++) {
				TextMessage message = session
						.createTextMessage("message de test en mode mesage");
				producer.send(message);
			}

			// Then receive the same number of messages that were sent
			for (int i = 0; i < 5; i++) {
				TextMessage message = (TextMessage) consumer.receive(5000);
				System.out.println(message.getText());
				// log.info("Received message with content " +
				// message.getText());
			}

		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} finally {

			// closing the connection takes care of the session, producer, and
			// consumer
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

}
