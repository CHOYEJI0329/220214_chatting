package com.project.first.ticket.controller;

import com.project.first.ticket.model.Login;
import com.project.first.ticket.model.User;
import com.project.first.ticket.service.ConfirmationTokenService;
import com.project.first.ticket.service.LoginService;
import com.project.first.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

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
        login.setEnabled(0);
        loginService.save(login);
        confirmationTokenService.createEmailConfirmationToken(String.valueOf(user.getIdx()), user.getEmail());

        return "redirect:/";
    }

    @GetMapping("confirm_email")
    public String viewConfirmEmail(@Valid @RequestParam String token) throws Exception {

        loginService.confirmEmail(token);
        return "redirect:/account/login";
    }


}
