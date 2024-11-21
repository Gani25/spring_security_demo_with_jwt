package com.sprk.spring_security_demo.repository;

import com.sprk.spring_security_demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

//    CUSTOM METHOD TO FIND USER WITH USERNAME
    Optional<UserInfo> findByUsername(String username);
}
