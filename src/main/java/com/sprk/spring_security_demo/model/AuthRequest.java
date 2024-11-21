package com.sprk.spring_security_demo.model;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;

    private String password;
}
