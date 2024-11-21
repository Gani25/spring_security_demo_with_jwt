package com.sprk.spring_security_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    private String password;

    private String roles;
}
