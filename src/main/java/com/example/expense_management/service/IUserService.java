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
    User findByUsername(String username);
    User findById(int id);
    LoginMessage login(LoginDTO loginDTO);

    Optional<User> findUserByUsernameAndPassword(String username,String password);


}
