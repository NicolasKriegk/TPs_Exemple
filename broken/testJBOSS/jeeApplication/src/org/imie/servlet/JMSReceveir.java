package org.imie.servlet;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class JMSReceveir implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(message.getStringProperty("test"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
