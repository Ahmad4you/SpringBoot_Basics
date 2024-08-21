package home.ahmad.boot_actuator.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Eigene Benutzer und Rollen definieren
 * mit standard login-Form
 * 
 * @author Ahmad Alrefai
 */
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
                       .password("{noop}password")  // {noop} zum Ignorieren der Passwortverschlüsselung
                       .roles("USER")
                       .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())  // Use withDefaults() for form login configuration
            .httpBasic(withDefaults()); // Use withDefaults() for basic authentication

        return http.build();
    }
}