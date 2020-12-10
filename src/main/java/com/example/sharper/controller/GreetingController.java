package com.example.sharper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Matvey on Nov, 2020
 */

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(Model model){
        return "greeting_page";
    }

}
