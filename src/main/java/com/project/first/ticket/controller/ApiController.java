package com.project.first.ticket.controller;

import com.project.first.ticket.Repository.UserRepository;
import com.project.first.ticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/nicknameChange", method = RequestMethod.POST)
    public boolean nicknameChange(@RequestBody User user){
        boolean result = userRepository.existsByNickname(user.getNickname());

        return result;
    }

}
