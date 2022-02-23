package com.project.first.ticket.Repository;

import com.project.first.ticket.model.Login;
import com.project.first.ticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Long> {

    boolean existsById (String nickName);

    Login findByIdAndEnabled (String id, Integer enabled);

    List<Login> findAllByUserIdx(Long userIdx);

}
