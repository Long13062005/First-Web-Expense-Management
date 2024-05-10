package com.example.expense_management.controller;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.model.User;
import com.example.expense_management.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/", "/login"})
public class LoginController {
    @Autowired
    IUserService userService;
    private LoginMessage loginMessage;


    @GetMapping()
    public String loginPage(Model model) {
        model.addAttribute("loginUser",new LoginDTO());
        return "login";
    }
}
