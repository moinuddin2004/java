package com.example.dreamshop.repo;

import com.example.dreamshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String roleUser);
}
