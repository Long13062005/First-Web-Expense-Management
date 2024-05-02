package com.example.expense_management.controller;

import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.dto.UserDTO;
import com.example.expense_management.exception.RecordNotFoundException;
import com.example.expense_management.model.User;
import com.example.expense_management.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class RegisterController {
    @Autowired
    IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }


    @PostMapping("/register")
    public String save(@Valid @ModelAttribute("user") UserDTO userDTO,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) throws RecordNotFoundException {

        if(bindingResult.hasErrors()) {
            return "signup";
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        User user = new User();
            BeanUtils.copyProperties(userDTO,user);
            user.setRoleName("User");
            user.setRoleId(2);
            userService.save(user);
            redirectAttributes.addFlashAttribute("success", "User is registed Successfully.......");
        return "redirect:/";
    }
}
