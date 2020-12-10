package com.example.sharper.controller;

import com.example.sharper.domain.Role;
import com.example.sharper.domain.User;
import com.example.sharper.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Matvey on Dec, 2020
 *
 * Administration panel
 */
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String getUsers(Model model){

        model.addAttribute("users", userRepo.findAll());

        return "admin_page";
    }

    @GetMapping("{user}")
    public String editUser(@PathVariable User user,
                           Model model){
        model.addAttribute("my_user", user);
        model.addAttribute("roles", Role.values());

        return "edit_user";
    }

    /**
     * Reformat user
     * @param username new username
     * @param password new password
     * @param form other parameters, including new roles
     * @param user my_user by id
     */
    @PostMapping
    public String userSave(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user
    ){
        user.setUsername(username);
        user.setPassword(password);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        // For following rewriting
        user.getRoles().clear();

        // Write new roles to clear role list of user
        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }

}
