package com.home.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.model.Role;
import com.home.repository.RoleRepository;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
    
    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        Role existingRole = roleRepository.findById(role.getId())
            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + role.getId()));
        existingRole.setRole(role.getRole());
        roleRepository.save(existingRole);
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId.intValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

}
