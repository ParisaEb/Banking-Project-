package com.example.TheBankingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TheBankingApp.Security.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName(String name);
    Role findById( long id);
    
}

