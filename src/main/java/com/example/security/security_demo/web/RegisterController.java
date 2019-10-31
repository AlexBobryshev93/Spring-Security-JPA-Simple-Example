package com.example.security.security_demo.web;

import com.example.security.security_demo.model.User;
import com.example.security.security_demo.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public RegisterController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("errMsg", "");
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("errMsg", "Error: check your password and try again");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        System.out.println(userRepo.findByUsername(user.getUsername()));
        return "redirect:/login";
    }
}
