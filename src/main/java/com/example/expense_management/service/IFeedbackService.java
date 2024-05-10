package com.example.expense_management.service;

import com.example.expense_management.model.Expenses;
import com.example.expense_management.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    List<Feedback> showList();
    List<Feedback> showListById(Integer userID);

    void save(Feedback feedback);
    void delete(int id);
    Optional<Feedback> findById(int id);



}
