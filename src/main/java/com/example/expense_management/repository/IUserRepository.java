package com.example.expense_management.repository;

import com.example.expense_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    Page<User> findUserByEmailContaining(String email, Pageable pageable);
    Optional<User> findOneByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
    public Optional<User> findById(long id);

}
