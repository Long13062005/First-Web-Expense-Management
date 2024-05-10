package com.example.expense_management.repository;

import com.example.expense_management.model.Expenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IExpenseRepository extends JpaRepository<Expenses,Integer> {
    Page<Expenses> findExpensesByDateContaining(Date date, Pageable pageable);
}
