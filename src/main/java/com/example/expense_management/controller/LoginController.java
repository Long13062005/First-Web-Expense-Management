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

<<<<<<< HEAD

    @GetMapping()
    public String loginPage(Model model) {
        model.addAttribute("loginUser",new LoginDTO());
        return "login";
    }
=======
    @GetMapping()
    public String loginPage(@ModelAttribute LoginDTO loginDTO, Model model) {
        model.addAttribute("loginUser", new LoginDTO());
        return "login";
    }

    @PostMapping()
    public String showLoginForm(@ModelAttribute("loginUser") LoginDTO loginDTO, Model model) {
        loginMessage = userService.loginUser(new LoginDTO(loginDTO.getEmail(), loginDTO.getPassword()));
        if (loginMessage.getMessage().equals("Login Success") )  {
            User user = userService.findByEmail(loginDTO.getEmail());
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

>>>>>>> c8d0a609c3efe2b6e9b6a7c7a62170dc06a2de8f
}
