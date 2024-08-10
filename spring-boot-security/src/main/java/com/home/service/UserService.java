package com.home.service;

import java.util.List;

import com.home.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
	
	void updateUser(User user); 
    void deleteUser(Long userId); 
    List<User> findAllUsers(); 
	
}