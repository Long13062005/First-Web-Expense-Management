package com.example.expense_management.repository;

import com.example.expense_management.model.Feedback;
import com.example.expense_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> searchAllById(Integer userID);
}
