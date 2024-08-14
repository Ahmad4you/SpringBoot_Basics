package home.ahmad.bootAutoConfig.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * wie man externe Konfigurationen (application.properties) in eine Spring Boot-Anwendung integriert und verwendet
 * 
 * 
 * @author Ahmad Alrefai
 */

@SpringBootApplication
public class SpringBootConfigApplication {

	private static Logger log = LoggerFactory.getLogger(SpringBootConfigApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigApplication.class, args);
	}
	
	/*
	 * @Value wird für die direkte Zuordnung einzelner Eigenschaften verwendet,
	 * @ConfigurationProperties eine Gruppe verwandter Eigenschaften zu einer Klasse ordnet.
	 * 
	 */
	
	@Value("${myapp.server-ip}")
	String serverIp;
	
	@Autowired
	MyAppProperties props;
	
	/*
	 * Eine funktionale Schnittstelle in Spring Boot, die verwendet wird, um Code auszuführen, 
	 * sobald die Anwendung vollständig gestartet ist. 
	 * In diesem Fall wird beim Starten der Anwendung die Methode values() ausgeführt.
	 * 
	 */
	
	@Bean
	CommandLineRunner values(){
		return args -> {
			log.info(" > The Server IP is: " + serverIp);
			log.info(" > App Name: " + props.getName());
			log.info(" > App Info: " + props.getDescription());
		};
	}
	
	/*
	 * Diese innere Klasse ist eine Konfigurationsklasse, die mit @ConfigurationProperties annotiert ist, 
	 * um eine Gruppe von Eigenschaften aus einer Konfigurationsdatei (application.properties) zu laden.
	 * 
	 */
	
	@Component
	@ConfigurationProperties(prefix="myapp")
	public static class MyAppProperties {
		private String name;
		private String description;
		private String serverIp;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getServerIp() {
			return serverIp;
		}
		public void setServerIp(String serverIp) {
			this.serverIp = serverIp;
		}
	}
	
}
