package com.sprk.spring_security_demo.controller;

import com.sprk.spring_security_demo.model.AuthRequest;
import com.sprk.spring_security_demo.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/authenticate")
    // user name and password
    public String generateToken(@RequestBody AuthRequest authRequest){

//        return jwtService.generateToken(authRequest);

//        DB Auth -> User name and password we have to verify

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {

            return jwtService.generateToken(authRequest);

        } else {
            throw new UsernameNotFoundException("Invalid user/Credentials please check again!!!");
        }
    }

}
