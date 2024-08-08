package ahmad.home.loginform.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Ahmad Alrefai
 */
public class UserService {
	
	
	/*
	 * SecurityContextHolder: Wenn man den Benutzernamen und das Passwort nach einer erfolgreichen Anmeldung verwenden möchtest
	 */
    public void authenticat() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            
            // Wenn man Zugriff auf das `UserDetails`-Objekt benötigst
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String password = ((UserDetails) principal).getPassword();
                // Weitere Verwendung von Benutzername und Passwort
            }
            
           
        }
    }
}
