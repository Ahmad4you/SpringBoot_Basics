package home.ahmad.boot_jpa_jdbc.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SimpleJdbcAppApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(SimpleJdbcAppApplication.class);
	
	@Autowired
	UserJDBCService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleJdbcAppApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		log.info("@@ Inserting Data....");
		service.insertData();
		log.info("@@ findAll() call...");
		service.findAll().forEach(entry -> log.info(entry.toString()));
	}
}