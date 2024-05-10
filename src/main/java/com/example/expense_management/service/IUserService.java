package com.example.expense_management.service;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.exception.RecordNotFoundException;
import com.example.expense_management.model.Expenses;
import com.example.expense_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    Page<User> findUserByUsernameContaining(String username, Pageable pageable);
    boolean save(User user) throws RecordNotFoundException;
    void delete(int id);
<<<<<<< HEAD
    User findByUsername(String username);
    User findById(int id);
    LoginMessage login(LoginDTO loginDTO);

    Optional<User> findUserByUsernameAndPassword(String username,String password);

=======
    Optional<User> findById(int id);
    LoginMessage loginUser(LoginDTO loginDTO);
    User findByEmail(String email);
>>>>>>> c8d0a609c3efe2b6e9b6a7c7a62170dc06a2de8f

}
