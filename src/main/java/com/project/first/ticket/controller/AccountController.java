package com.project.first.ticket.controller;

import com.project.first.ticket.model.Login;
import com.project.first.ticket.model.User;
import com.project.first.ticket.service.LoginService;
import com.project.first.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

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

    @PostMapping("/register")
    public String registerPost(User user, HttpServletRequest request){

        user = userService.save(user);

        Login login = new Login();
        login.setId(request.getParameter("id"));
        login.setPw(request.getParameter("pw"));
        login.setUserIdx(user.getIdx());
        login = loginService.save(login);

        return "account/login";
    }


}
