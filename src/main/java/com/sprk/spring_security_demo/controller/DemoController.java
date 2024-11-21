package com.sprk.spring_security_demo.controller;

import com.sprk.spring_security_demo.entity.UserInfo;
import com.sprk.spring_security_demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserInfo userInfo) {

        return userInfoService.saveUser(userInfo);

    }

    @GetMapping("/welcome")
    public String showWelcome() {
        return "Welcome to Spring Security Demo";
    }

    //    OPEN TO ANYONE WITHOUT LOGIN
    @GetMapping({"/home", "/"})
    public String showHome() {
        return "Home Page of Spring Security";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showAdmin() {
        return "Admin Page of Spring Security";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String showUser() {
        return "User Page of Spring Security";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public String showManager() {
        return "Manager Page of Spring Security";
    }


    //    Only ADMIN can add role to existing users
    @PutMapping("/add_role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addRoleToUser(@PathVariable int id, @RequestParam String role) {
        return userInfoService.addRoleToUser(id, role);
    }

    @PutMapping("/delete_role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRoleToUser(@PathVariable int id, @RequestParam String role) {
        return userInfoService.deleteRoleToUser(id, role);
    }
}
