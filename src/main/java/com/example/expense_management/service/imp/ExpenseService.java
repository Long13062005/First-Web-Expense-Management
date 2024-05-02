package com.example.expense_management.service.imp;

import com.example.expense_management.model.Expenses;
import com.example.expense_management.repository.IExpenseRepository;
import com.example.expense_management.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ExpenseService implements IExpenseService {
    @Autowired
    IExpenseRepository iExpenseRepository;
    @Override
    public Page<Expenses> findExpensesByDateContaining(Date date, Pageable pageable) {
        return iExpenseRepository.findExpensesByDateContaining(date,pageable);
    }

    @Override
    public void save(Expenses expenses) {
        iExpenseRepository.save(expenses);
    }

    @Override
    public void delete(int id) {
        iExpenseRepository.deleteById(id);
    }

    @Override
    public Optional<Expenses> findById(int id) {
        return iExpenseRepository.findById(id);
    }

    @Override
    public Page<Expenses> searchListByDate(Pageable pageable, Date date) {
        return null;
    }
}
