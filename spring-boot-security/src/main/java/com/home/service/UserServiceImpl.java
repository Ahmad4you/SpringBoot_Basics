package com.home.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.home.model.Role;
import com.home.model.User;
import com.home.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 
 * @author Ahmad Alrefai
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleService roleService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
		this.roleService = roleService;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        
        Role userRole = roleService.findByRole("ADMIN");
        if (userRole == null) {
            userRole = new Role();
            userRole.setRole("ADMIN");
            roleService.saveRole(userRole);
        }
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }
    
    @Override
    public void updateUser(User user) {
        
        User existingUser = userRepository.findById((long)user.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found: " + user.getEmail()));
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setActive(user.isActive());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(userName);
//        Set<Role> roles = user != null ? user.getRoles() : new TreeSet<>();
//        List<GrantedAuthority> authorities = getUserAuthority(roles);
//        return buildUserForAuthentication(user, authorities, userName);
//    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), 
            user.getPassword(), 
            authorities
        );
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities, String userName) {
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
        } else {
            logger.info("User not found: " + userName);
            throw new UsernameNotFoundException("User not found: " + userName);
        }
    }
}