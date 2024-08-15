package home.ahmad.springMVC01.springxml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/soindex
 * 
 * @author Ahmad Alrefai
 */

@RestController
@ImportResource({"META-INF/spring/services-context.xml"})
@SpringBootApplication
public class SpringXmlApplication {

	@Autowired
	SimpleService html;

	@RequestMapping("/soindex")
	public String index(){
		return html.getHtmlH1From("Using Spring XML beans in here!");
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringXmlApplication.class, args);
	}
}
