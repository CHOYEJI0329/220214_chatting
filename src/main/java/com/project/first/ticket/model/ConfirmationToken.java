package com.project.first.ticket.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConfirmationToken {

    private static final Long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column
    private String id;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column
    private boolean expired;

    @Column(name = "user_idx")
    private String userIdx;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    /**
     * 이메일 인증 토큰 생성
     * @param userIdx
     * @return
     */
    public static ConfirmationToken createEmailConfirmationToken(String userIdx){
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        confirmationToken.userIdx = userIdx;
        confirmationToken.expired = false;
        return confirmationToken;
    }

    /**
     * 토큰 사용으로 인한 만료
     */
    public void useToken(){
        expired = true;
    }

}
