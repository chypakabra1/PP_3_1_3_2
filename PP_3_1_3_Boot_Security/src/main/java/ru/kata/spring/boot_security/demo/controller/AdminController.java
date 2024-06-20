package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceConfig;

import java.security.Principal;

@RestController
public class AdminController {

    private final UserServiceConfig userServiceConfig;

    public AdminController(UserServiceConfig userServiceConfig) {
        this.userServiceConfig = userServiceConfig;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String authenticated(Principal principal) {
        User user = userServiceConfig.findByUsername(principal.getName());
        return "secured part of web service: " + user.getUsername() + " " + user.getEmail();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "read profile page";
    }

    //@Secured("ROLE_ADMIN")
    @GetMapping("/only_for_admins")
    public String pageOnlyForAdmins() {
        return "admins page";
    }

}