package com.example.sharper.controller;

import com.example.sharper.domain.Role;
import com.example.sharper.domain.User;
import com.example.sharper.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * Created by Matvey on Nov, 2020
 */
@Controller
public class RegistrationController {

    private UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String userRegistration(Model model){
        return "registration";
    }

    /**
     * Registration of new user with validation by username
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
