package com.project.first.ticket.service;

import com.project.first.ticket.Repository.LoginRepository;
import com.project.first.ticket.model.ConfirmationToken;
import com.project.first.ticket.model.Login;
import com.project.first.ticket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    public Login save(Login login){

        String encodePw = passwordEncoder.encode(login.getPw());
        login.setPw(encodePw);

        return loginRepository.save(login);
    }

    public boolean existsByIdAndPw(Login login){
        String pw = login.getPw();
        login = loginRepository.findByIdAndEnabled(login.getId(), 1);
        boolean result = false;
        if(login != null) result = passwordEncoder.matches(pw, login.getPw());

        return result;
    }

    /**
     * 이메일 인증 로직
     * @param token
     */
    public void confirmEmail(String token) throws Exception {
        ConfirmationToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
        Login login = loginRepository.findByUserIdx(Long.valueOf(findConfirmationToken.getUserIdx()));
        findConfirmationToken.useToken();
        login.setEnabled(1);
        login = loginRepository.save(login);
    }

}
