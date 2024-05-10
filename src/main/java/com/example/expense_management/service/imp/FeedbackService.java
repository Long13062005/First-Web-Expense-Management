package com.example.expense_management.service.imp;

import com.example.expense_management.model.Feedback;
import com.example.expense_management.repository.IFeedbackRepository;
import com.example.expense_management.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    IFeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> showList() {
        return null;
    }

    @Override
    public List<Feedback> showListById(Integer userID) {
        return null;
    }

    @Override
    public void save(Feedback feedback) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Feedback> findById(int id) {
        return Optional.empty();
    }
}
