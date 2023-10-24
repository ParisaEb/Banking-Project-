package com.example.TheBankingApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TheBankingApp.Repository.RoleRepository;
import com.example.TheBankingApp.Security.Role;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }
    
    public List<Role> getRolesByName(String name) {
        return roleRepository.findByName(name);
    }
    

}