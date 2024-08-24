package home.ahmad.boot_joinfaces.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 
 * @author Ahmad Alrefai
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())  // CSRF-Schutz deaktivieren
//            .authorizeHttpRequests(authz -> authz
//                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**","/jakarta.faces" + ".resource/**",  "/images/**").permitAll()
//                .requestMatchers("/login.xhtml", "/index.xhtml").permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin(formLogin -> formLogin
//                .loginPage("/login.xhtml")  
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .defaultSuccessUrl("/index.xhtml", true)
//                .failureUrl("/login.xhtml?error=true")
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .permitAll()
//            )
//            .authenticationProvider(authenticationProvider());
//
//        return http.build();
//    }
	
	/**
	 * InMemoryUserDetails speichern!
	 * 
	 * @author Ahmad Alrefai
	 * @param http
	 * @return
	 * @throws Exception
	 */
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	        .csrf(csrf -> csrf.disable())  // CSRF-Schutz deaktivieren
	        .authorizeHttpRequests(authz -> authz
	                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**","/jakarta.faces" + ".resource/**",  "/images/**", "/api/**").permitAll()
	                .requestMatchers("/login.xhtml", "/index.xhtml", "/restCustomer.xhtml").permitAll()
	                .anyRequest().authenticated()
	            )
//	        .csrf(csrf -> csrf
//	                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//	            )
//	            .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
//	            .formLogin(withDefaults())
//	            .httpBasic(withDefaults())
	        
	        .formLogin(formLogin -> formLogin
	                .defaultSuccessUrl("/restCustomer", true)
//	                .defaultSuccessUrl("/index", true)
	                .failureUrl("/login.xhtml?error=true")
	                .permitAll()
	            )
	        .httpBasic(withDefaults());

	        return http.build();
	    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("ahmad123"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("adminpass"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
