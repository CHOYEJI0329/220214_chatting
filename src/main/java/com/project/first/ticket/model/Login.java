package com.project.first.ticket.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String id;
    private String pw;
    @Column(name = "user_idx")
    private Long userIdx;
    private Integer enabled;

}
