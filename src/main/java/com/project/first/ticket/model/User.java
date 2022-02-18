package com.project.first.ticket.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String birthday;
    private Integer gender;
    private String nickname;
    private String email;
    private String img_path;

    @OneToMany
    @JoinColumn(name = "login_idx")
    private List<Login> login = new ArrayList<>();

}
