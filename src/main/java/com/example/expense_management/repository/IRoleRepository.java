package com.example.expense_management.repository;

import com.example.expense_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
        Role findByName(String name);
        }
