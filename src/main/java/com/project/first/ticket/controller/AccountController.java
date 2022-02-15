package com.project.first.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login(Model model){

        return "account/login";
    }

    @GetMapping("/register")
    public String register(Model model){

        return "account/register";
    }

    @GetMapping("/find")
    public String find(Model model){

        return "account/find";
    }


}