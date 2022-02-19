package com.project.first.ticket.service;

import com.project.first.ticket.Repository.LoginRepository;
import com.project.first.ticket.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Login save(Login login){

        String encodePw = passwordEncoder.encode(login.getPw());
        login.setPw(encodePw);
        login.setEnabled(1);

        return loginRepository.save(login);
    }

    public boolean existsByIdAndPw(Login login){
        String pw = login.getPw();
        login = loginRepository.findByIdAndEnabled(login.getId(), 1);
        boolean result = false;
        if(login != null) result = passwordEncoder.matches(pw, login.getPw());

        return result;
    }

}
