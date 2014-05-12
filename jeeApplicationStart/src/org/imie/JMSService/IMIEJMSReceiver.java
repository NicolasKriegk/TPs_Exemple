package org.imie.JMSService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class IMIEJMSReceiver implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(message.getStringProperty("message"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
