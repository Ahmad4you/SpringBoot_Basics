package com.home.service;

import java.util.List;

import com.home.model.Role;

public interface RoleService {
	Role findByRole(String role);
	void saveRole(Role role); 
    void updateRole(Role role); 
    void deleteRole(Long roleId); 
    List<Role> findAllRoles();

}
