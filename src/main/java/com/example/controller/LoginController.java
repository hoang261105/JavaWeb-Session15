package com.example.controller;

import com.example.model.User;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("login-user")
    public String loginUser(User user, Model model, HttpSession session) {
        boolean result = userService.checkLogin(user.getEmail(), user.getPassword());

        if (result) {
            User fullUser = userService.findByEmail(user.getEmail());
            session.setAttribute("user", fullUser);
            return "redirect:/products";
        }

        model.addAttribute("message", "Email or password incorrect");
        return "login";
    }

}
