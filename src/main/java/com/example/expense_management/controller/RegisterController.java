package com.example.expense_management.controller;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.dto.UserDTO;
import com.example.expense_management.exception.RecordNotFoundException;
import com.example.expense_management.model.User;
import com.example.expense_management.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    IUserService userService;
    LoginMessage loginMessage;
    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "signup";
    }
    @PostMapping
    public String save(@Valid @ModelAttribute("user") UserDTO userDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,Model model) throws RecordNotFoundException {

        if(bindingResult.hasErrors()) {
            return "signup";
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        if(!userService.save(user)) {
            model.addAttribute("usernameTaken", loginMessage.getMessage());
            model.addAttribute("user", new UserDTO());
            return "signup";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("usernameTaken", "User is registed Successfully.......");
        return "redirect:/";
    }

}
