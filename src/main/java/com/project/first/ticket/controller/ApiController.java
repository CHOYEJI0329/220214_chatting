package com.project.first.ticket.controller;

import com.project.first.ticket.Repository.LoginRepository;
import com.project.first.ticket.Repository.UserRepository;
import com.project.first.ticket.model.Login;
import com.project.first.ticket.model.User;
import com.project.first.ticket.service.LoginService;
import com.project.first.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/nicknameChange", method = RequestMethod.POST)
    public boolean nicknameChange(@RequestBody User user){
        boolean result = userRepository.existsByNickname(user.getNickname());

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/idChange", method = RequestMethod.POST)
    public boolean idChange(@RequestBody Login login){
        boolean result = loginRepository.existsById(login.getId());

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestBody Login login){
        boolean result = loginService.existsByIdAndPw(login);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    public List<Login> findId(@RequestBody User user){

        user = userRepository.findByNameAndBirthday(user.getName(), user.getBirthday());
        List<Login> loginList = new ArrayList<>();

        if(user != null) {
            loginList = loginRepository.findAllByUserIdx(user.getIdx());
        }

        return loginList;
    }

}
