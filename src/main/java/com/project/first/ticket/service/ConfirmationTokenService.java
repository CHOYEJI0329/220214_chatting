package com.project.first.ticket.service;

import com.project.first.ticket.Repository.ConfirmationTokenRepository;
import com.project.first.ticket.model.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final SendEmailService sendEmailService;

    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    public String createEmailConfirmationToken(String userIdx, String receiverEmail){

        Assert.hasText(userIdx, "userIdx는 필수 입니다.");
        Assert.hasText(receiverEmail, "receiverEmail는 필수 입니다.");

        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(userIdx);
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText("http://localhost:8080/account/confirm_email?token="+emailConfirmationToken.getId());
        sendEmailService.sendEmail(mailMessage);

        return emailConfirmationToken.getId();
    }

    /**
     * 유효한 토큰 가져오기
     * @param confirmationTokenId
     * @return
     */
    public ConfirmationToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId) throws Exception {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        return confirmationToken.orElseThrow(() -> new Exception("token이 없습니다."));
    }

}
