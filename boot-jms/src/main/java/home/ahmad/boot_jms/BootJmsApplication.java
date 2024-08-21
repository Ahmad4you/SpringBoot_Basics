package home.ahmad.boot_jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import home.ahmad.boot_jms.config.message.Producer;


/**
 * JMS wird verwendet, um Nachrichten zwischen verschiedenen Komponenten einer Anwendung zu senden und zu empfangen, oft in einem asynchronen Modus.
 * myqueue: Der Name der Warteschlange, die in der Anwendung verwendet wird.
 * 
 * Der Producer sendet eine Nachricht an eine Warteschlange.
 * Der Consumer empfängt die Nachricht aus der Warteschlange und verarbeitet sie.
 * Die MessagingConfig-Klasse konfiguriert die JMS-Infrastruktur und verbindet den Consumer mit der Warteschlange.
 * 
 * @author Ahmad Alrefai
 */

@SpringBootApplication
public class BootJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJmsApplication.class, args);
	}
	
	@Value("${myqueue}")
	String queue;
	
	@Bean
	CommandLineRunner sendMessage(JmsTemplate jmsTemplate){
		return args -> {
			Producer producer = new Producer(jmsTemplate);
			producer.sendTo(queue, "Spring Boot Rocks!");
		};
	}

}
