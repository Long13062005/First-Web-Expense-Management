package com.example.expense_management.controller;

import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.model.User;
import com.example.expense_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@ModelAttribute LoginDTO loginDTO) {
        return "login";
    }

    @PostMapping("/login")
    public String showLoginForm(@ModelAttribute User user, Model model) {
        if (user.getRoleId() == 1) {
            model.addAttribute("firstname", user.getFirstName());
            model.addAttribute("lastname", user.getLastName());
            return "admin/adminHome";
        } else {
            return "user/userHome";
        }
    }

}
