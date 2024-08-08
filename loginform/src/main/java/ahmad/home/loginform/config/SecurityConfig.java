package ahmad.home.loginform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ahmad.home.loginform.service.CustomAuthenticationProvider;

/**
 * 
 * @author Ahmad Alrefai
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }
	
//	 @Autowired
//	 private CustomAuthenticationProvider customAuthenticationProvider;


	 @Bean
	    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	        http
	            .csrf(AbstractHttpConfigurer::disable)
	            .authorizeHttpRequests(authz -> authz
	                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**","/jakarta.faces" + ".resource/**",  "/images/**").permitAll()
	                .requestMatchers("/login.faces", "/index.faces").permitAll()  // Sicherstellen, dass diese URLs zugelassen sind
	                .anyRequest().authenticated()
	            )
	            .formLogin(formLogin -> formLogin
	                .loginPage("/login")  
	                .loginProcessingUrl("/j_spring_security_check")  // URL zur Verarbeitung von Login-Anfragen
	                .usernameParameter("j_username")
	                .passwordParameter("j_password")
	                .defaultSuccessUrl("/index", true)
	                .failureUrl("/login?error=true")
	                .permitAll()
	            )
	           // Registrierung des AuthenticationProvider in der Sicherheitskonfiguration 
	        .authenticationProvider(customAuthenticationProvider());
	        
	        return http.build();
	    }
	 
	 

		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
			return authConfig.getAuthenticationManager();
		}
		

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
