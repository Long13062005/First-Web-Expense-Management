package com.example.expense_management.controller;

import com.example.expense_management.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    IExpenseService expenseService;


}
