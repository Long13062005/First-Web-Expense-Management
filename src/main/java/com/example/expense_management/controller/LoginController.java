package com.example.expense_management.controller;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.model.User;
import com.example.expense_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/login"})
public class LoginController {
    @Autowired
    IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private LoginMessage loginMessage;

    @GetMapping()
    public String loginPage(@ModelAttribute LoginDTO loginDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "login";

        }
        return "login";
    }

    @PostMapping()
    public String showLoginForm(@ModelAttribute User user, Model model) {
        loginMessage = userService.loginUser(new LoginDTO(user.getEmail(), user.getPassword()));
        if (loginMessage.getMessage().equals("Login Success") )  {
            if (user.getRoleId() == 1) {
                model.addAttribute("firstname", user.getFirstName());
                model.addAttribute("lastname", user.getLastName());
                return "admin/adminHome";
            } else {
                model.addAttribute("firstname", user.getFirstName());
                model.addAttribute("lastname", user.getLastName());
                return "user/userHome";
            }
        } else {
            model.addAttribute("error", loginMessage.getMessage());
            return "login";
        }

    }

}
