package com.example.expense_management.repository;

import com.example.expense_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    Page<User> findUserByUsernameContaining(String username, Pageable pageable);
    @Query(nativeQuery = true,value = "SELECT * FROM User u WHERE u.username = :username")
    User findUserByUsername(String username);
    @Query(nativeQuery = true,value = "SELECT * FROM User u WHERE u.username = :username AND u.password = :password")
    User login(String username,String password);
    @Query(nativeQuery = true,value = "SELECT * FROM User u WHERE u.id = :id")

    User findById(long id);

    Optional<User> findUserByUsernameAndPassword(String username,String password);


}
