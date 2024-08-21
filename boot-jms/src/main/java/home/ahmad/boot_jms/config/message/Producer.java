package home.ahmad.boot_jms.config.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	private JmsTemplate jmsTemplate;
	
	public Producer(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
	
	/**
	 * Diese Methode sendet eine Nachricht (message) an eine spezifische JMS-Warteschlange (queue). 
	 * Die Methode convertAndSend() wandelt die Nachricht in das benötigte Format um und sendet sie dann an die angegebene Warteschlange.
	 * 
	 * @param queue
	 * @param message
	 */
	public void sendTo(String queue, String message) {
		this.jmsTemplate.convertAndSend(queue, message);
		log.info("Producer> Message Sent");
	}
}
