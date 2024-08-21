package home.ahmad.boot_jms.config.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

public class Consumer implements MessageListener{
	private Logger log = LoggerFactory.getLogger(Consumer.class);
	
	/**
	 * Diese Methode verarbeitet die empfangene Nachricht. 
	 * In diesem Beispiel wird der Nachrichteninhalt (message.getBody(Object.class)) protokolliert.
	 * 
	 */
	@Override
	public void onMessage(Message message) {
		try {
			log.info("Consumer> " + message.getBody(Object.class));
		}catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

}