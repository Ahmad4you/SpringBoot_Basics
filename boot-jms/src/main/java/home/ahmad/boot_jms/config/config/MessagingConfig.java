package home.ahmad.boot_jms.config.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import home.ahmad.boot_jms.config.message.Consumer;
import jakarta.jms.ConnectionFactory;

@Configuration
public class MessagingConfig {

	@Autowired
	private ConnectionFactory connectionFactory;
	
	/**
	 * Diese Annotation weist Spring an, den Wert der queue-Variablen aus der Konfigurationsdatei (z.B. application.properties) zu laden.
	 */
	@Value("${myqueue}")
	private String queue;
	
	/**
	 * Diese Bean wird erstellt, um Nachrichten aus der angegebenen Warteschlange (queue) zu empfangen. 
	 * Sie verbindet die Consumer-Klasse mit der Warteschlange und sorgt dafür, dass onMessage() aufgerufen wird, wenn eine Nachricht empfangen wird.
	 * 
	 * @return
	 */
	@Bean
	public DefaultMessageListenerContainer messageListener() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(this.connectionFactory);
		container.setDestinationName(queue);
		container.setMessageListener(new Consumer());
		return container;
	}
}
