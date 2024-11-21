package com.sprk.spring_security_demo.service;

import com.sprk.spring_security_demo.config.CustomUserDetails;
import com.sprk.spring_security_demo.entity.UserInfo;
import com.sprk.spring_security_demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> optionalUserInfo =  userInfoRepository.findByUsername(username);

        if(optionalUserInfo.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }else{
            UserInfo userInfo = optionalUserInfo.get(); // username, password and roles
            return new CustomUserDetails(userInfo);
        }
    }
}
