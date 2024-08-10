package com.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 
 * @author Ahmad Alrefai
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Qualifier("userServiceImpl") // oder "userService", je nachdem, welche Implementierung verwenden möchten
    @Autowired
    private UserDetailsService userDetailsService; 

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/jakarta.xhtml" +
                                        ".resource/**", "/resources/i18n/**", "/i18n/**", "/error/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/index.xhtml").permitAll()
                                .requestMatchers("/employees").permitAll()
                                .requestMatchers("/error-test").permitAll() //TODO remove this line
                                .requestMatchers("/login.xhtml").permitAll()
                                .requestMatchers("/registration.xhtml").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login.xhtml")
                        .loginProcessingUrl("/j_spring_security_check")
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")
                        .defaultSuccessUrl("/admin/home.xhtml", true)
                        .failureUrl("/login.xhtml?error=true")
                        .permitAll()
                    )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login.xhtml?logout")
                        .invalidateHttpSession(true)
                );
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
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // Setzen  den UserDetailsService
        provider.setPasswordEncoder(passwordEncoder()); // Setzen den PasswordEncoder
        return provider;
    }
}
