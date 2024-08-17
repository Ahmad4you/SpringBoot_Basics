package home.ahmad.boot_jpa_jdbc.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import home.ahmad.boot_jpa_jdbc.model.User;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service
public class UserJDBCService {
	private static final Logger log = LoggerFactory.getLogger(UserJDBCService.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	 public void insertData() {
	        log.info("> Table creation");
	        // Wenn die Tabelle bereits erstellt ist, kommentiere die Zeilen zum Löschen und Erstellen aus
	        // jdbcTemplate.execute("DROP TABLE IF EXISTS user");
	        // jdbcTemplate.execute("CREATE TABLE user(id BIGINT AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), age INT)");
	        log.info("> Inserting data...");
//	        jdbcTemplate.update("INSERT INTO user(first_name, last_name, age) VALUES (?, ?, ?)", "Ahmad", "Senior developer", 33);
//	        jdbcTemplate.update("INSERT INTO user(first_name, last_name, age) VALUES (?, ?, ?)", "Ahmad1", "Senior developer1", 33);
//	        jdbcTemplate.update("INSERT INTO user(first_name, last_name, age) VALUES (?, ?, ?)", "Ahmad2", "Senior developer2", 33);
	        
	        log.info("> Done.");
	    }

	    public List<User> findAll() {
	        String query = "SELECT * FROM user";
	        return jdbcTemplate.query(query, (rs, rowNum) -> 
	            new User(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("age")));
	    }
	
}
