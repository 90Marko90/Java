//package com.example.tenisuj.controller.web;
//
//import com.example.tenisuj.model.User;
//import com.example.tenisuj.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class LoginWeb {
//
//    private final UserService userService;
//
//    @Autowired
//    public LoginWeb(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        return "logout";
//    }
//
//    @GetMapping("/signup")
//    public String signup() {
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String processSignup(@ModelAttribute("user") User user) {
//        userService.addUser(user.getUsername(), user.getPassword());
//        return "redirect:/login";
//    }
//
//}