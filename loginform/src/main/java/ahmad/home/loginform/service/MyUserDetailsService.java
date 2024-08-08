package ahmad.home.loginform.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ahmad.home.loginform.model.Role;
import ahmad.home.loginform.model.User;
import ahmad.home.loginform.repository.UserRepository;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Dein Repository für Benutzer

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<Role> roles = new HashSet<>();
        Role role= new Role();
        role.setRole("ADMIN");
        roles.add(role);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getUserAuthority(roles)) ;
    }
    
    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }
}
