package com.project.first.ticket.service;

import com.project.first.ticket.Repository.UserRepository;
import com.project.first.ticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){

        return userRepository.save(user);
    }


}
