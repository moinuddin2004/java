package com.example.dreamshop.repo;

import com.example.dreamshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
}
