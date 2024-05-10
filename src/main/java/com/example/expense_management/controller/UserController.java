package com.example.expense_management.controller;

import com.example.expense_management.model.Expenses;
import com.example.expense_management.model.User;
import com.example.expense_management.service.IExpenseService;
import com.example.expense_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    IExpenseService expenseService;
    @Autowired
    IUserService userService;

//    @GetMapping("/{id}")
//    public String userHome(@PathVariable("id") int userID, Model model){
//        model.addAttribute("user",userService.findById(userID));
//        return "user/userHome";
//    }
    @GetMapping
    public String userHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
            model.addAttribute("user",user);
        return "home";
    }
    @GetMapping("/userList")
    public String showUserList(Model model, @RequestParam(defaultValue = "",required = false) String valueSearch,
                               @RequestParam(required = false, defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<User> users = userService.findUserByUsernameContaining(valueSearch,pageable);
        model.addAttribute("userList",users);
        model.addAttribute("valueSearch",valueSearch);
        return "admin/userList";
    }

    //wrong
    @GetMapping("/expenseList")
    public String showExpenseList(Model model, @RequestParam(defaultValue = "",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date valueSearch,
                               @RequestParam(required = false, defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Expenses> expense = expenseService.findExpensesByDateContaining(valueSearch,pageable);
        model.addAttribute("expenseList",expense);
        model.addAttribute("valueSearch",valueSearch);
        return "user/expenseList";
    }
    @PostMapping ("/delete")
    public String deleteUser(@RequestParam(value = "deleteId") int userID) {
        userService.delete(userID);
        return "redirect:/user/userList";
    }



}
