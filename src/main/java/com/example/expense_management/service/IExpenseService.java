package com.example.expense_management.service;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.model.Expenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IExpenseService {
    Page<Expenses> findExpensesByDateContaining(Date date, Pageable pageable);
    void save(Expenses expenses);
    void delete(int id);
    Optional<Expenses> findById(int id);

    Page<Expenses> searchListByDate(Pageable pageable, Date date);

}
