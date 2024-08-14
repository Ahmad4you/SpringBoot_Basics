package home.ahmad.bootAutoConfig;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


/**
 * 
 * @author Ahmad Alrefai
 */


@SpringBootApplication
public class BootAutoConfigApplication {
	private static final Logger log = LoggerFactory.getLogger(BootAutoConfigApplication.class);
	
//	1: Einfacher Start
	public static void main(String[] args) {
//		SpringApplication.run(BootAutoConfigApplication.class, args);
//	}
	
//	2: Manuelle Konfiguration vor dem Start
//	SpringApplication app = new SpringApplication(BootAutoConfigApplication.class);
//	fuege zusätzliche Konfigurationen hinzu, bevor die Anwendung gestartet wird.
	
//	app.run(args);
	
//	3: mein Banner definieren
		// wie ein benutzerdefiniertes Banner erstellen, das anstelle des Standard-Spring Boot-Banners beim Start angezeigt wird.
	SpringApplication app = new SpringApplication(BootAutoConfigApplication.class);
	app.setBanner(new Banner() {
		@Override
		public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
			out.print("\n\n\tAhmad banner!\n\n".toUpperCase());			
		}
	});
	app.run(args);

		
//	4: Standard-Banner deaktivieren
//	SpringApplication app = new SpringApplication(BootAutoConfigApplication.class);
//	app.setBannerMode(Mode.OFF);
//	app.run(args);
		
		
//		 5: Benutzer config
//		@Configuration
//		class MyConfig{
//			@Bean
//			String text(){
//				return "Hi there!";
//			}
//		}
		
//		new SpringApplicationBuilder(BootAutoConfigApplication.class)
//		.child(MyConfig.class)	
//	    .run(args);

		
		
		
//		6: Deaktivieren der Startinformationen
//		SpringApplicationBuilder: Es Ist eine Alternative zu SpringApplication und bietet mehr Flexibilität bei der Konfiguration.
//		new SpringApplicationBuilder(BootAutoConfigApplication.class)
//		.logStartupInfo(false)  // deaktiviert die Ausgabe der Startinformationen
//		.run(args);
		
		/*
		 * Profile: Ein Profil in Spring ist eine Umgebungsspezifische Konfiguration. Zum Beispiel kann man eine Konfigurationsdatei 
		 * application-production.properties haben, die Einstellungen für eine Produktionsumgebung enthält. 
		 * Durch das Aktivieren des production-Profils werden diese spezifischen Einstellungen angewendet.
		 * 
		 * 
		 */
		
//		7: Aktivieren von Profilen, legt das aktive Profil fest
//		new SpringApplicationBuilder(BootAutoConfigApplication.class)
//		.profiles("production")  // dev, test
//		.run(args);
		
		
		
//		 8. Add Listeners
//				
//				new SpringApplicationBuilder(BootAutoConfigApplication.class)
//				.listeners(new ApplicationListener<ApplicationEvent>() {
//				
//					@Override
//					public void onApplicationEvent(ApplicationEvent event) {
//						log.info("### > " + event.getClass().getCanonicalName());
//					}
//					
//				})
//				.run(args);
				
				
//				Version 9. Remove Web
//				new SpringApplicationBuilder(BootAutoConfigApplication.class)
//				.web(false)
//				.run(args);

	
	}
}





//public class SpringBootSimpleApplication implements CommandLineRunner, ApplicationRunner{

/*
 * ApplicationRunner vs. CommandLineRunner: Beide Interfaces dienen einem ähnlichen Zweck, 
 * nämlich Code nach dem Starten der Spring Boot-Anwendung auszuführen. Der Hauptunterschied besteht darin, 
 * dass ApplicationRunner eine ApplicationArguments-Instanz erhält, die mehr Struktur bietet und zwischen nicht-optionalen 
 * und optionalen Argumenten unterscheidet. CommandLineRunner hingegen erhält die Argumente als einfaches String-Array.
 * 
 */
//	@Bean
//	String info(){
//		return "Just a simple String bean";
//	}
//	
//	@Autowired
//	String info;
//	
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		log.info("ApplicationRunner Implementation...");
//		log.info("Info bean: " + info);
//		args.getNonOptionArgs().forEach(file -> log.info(file));
//	}
//	@Override
//	public void run(String... args) throws Exception {
//		log.info("CommandLineRunner Implementation...");
//		log.info("Info bean: " + info);
//		for(String arg:args)
//			log.info(arg);
//	}
//}
