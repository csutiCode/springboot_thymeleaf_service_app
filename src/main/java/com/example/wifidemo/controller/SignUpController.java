package com.example.wifidemo.controller;

import com.example.wifidemo.dto.UserDto;
import com.example.wifidemo.model.User;
import com.example.wifidemo.service.UserDetailsServiceImpl;
import com.example.wifidemo.validations.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/signUp")
    public String signUp(Model model, UserDto dto) {
        model.addAttribute("userDto", dto);
        return "signUp";
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute("userDto") UserDto dto, RedirectAttributes redirectAttributes) {
        if (userDetailsService.checkUserInDb(dto)) {
            redirectAttributes.addFlashAttribute("error", "Username is already used");
            System.out.println("Username is already used");
            return "redirect:/signUp";
        } else if (SignUpValidator.passwordCheck(dto)) {
            redirectAttributes.addFlashAttribute("error", "Passwords needs to be equal");
            System.out.println("Password problem");
            return "redirect:/signUp";
        }  else if (SignUpValidator.isNull(dto)) {
            redirectAttributes.addFlashAttribute("error", "You have to fill out every field");
            System.out.println("Password problem");
            return "redirect:/signUp";
        } else {
            userDetailsService.saveUser(dtoToUser(dto));
            return "redirect:/";
        }
    }

    public User dtoToUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }


}


