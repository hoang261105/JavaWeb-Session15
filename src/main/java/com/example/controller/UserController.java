package com.example.controller;

import com.example.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @GetMapping("register-success")
    public String registerSuccess() {
        return "register_success";
    }

    @PostMapping("register-user")
    public String registerUser(UserDTO userDTO, Model model) {
        String errorName = "";
        String errorEmail = "";
        String errorPassword = "";
        boolean isValid = true;
        
        if(userDTO.getName().isEmpty()){
            errorName = "Tên không được để trống!";
            isValid = false;
        }

        if(userDTO.getEmail().isEmpty()){
            errorEmail = "Email không được để trống!";
            isValid = false;
        }

        if(userDTO.getPassword().isEmpty()){
            errorPassword = "Mật khẩu không được để trống!";
            isValid = false;
        }

        if(isValid){
            return "redirect:/register-success";
        }

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("errorName", errorName);
        model.addAttribute("errorEmail", errorEmail);
        model.addAttribute("errorPassword", errorPassword);

        return "register";
    }
}
