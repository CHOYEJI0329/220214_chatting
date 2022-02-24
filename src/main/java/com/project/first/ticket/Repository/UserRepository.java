package com.project.first.ticket.Repository;

import com.project.first.ticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname (String nickName);

    User findIdxByNameAndBirthdayAndEmail (String name, String birthday, String email);

    User findByNameAndBirthday (String name, String birthday);

    User findByIdx (Long idx);

}
