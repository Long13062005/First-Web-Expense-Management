package com.example.expense_management.controller;

import com.example.expense_management.model.User;
import com.example.expense_management.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    public UserService userService;

    @GetMapping("/user")
    public String showUserList(Model model, @RequestParam(defaultValue = "",required = false) String valueSearch,
                               @RequestParam(required = false, defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<User> users = userService.findUserByEmailContaining(valueSearch,pageable);
        model.addAttribute("userList",users);
        model.addAttribute("valueSearch",valueSearch);
        return "admin/adminHome";
    }



}
