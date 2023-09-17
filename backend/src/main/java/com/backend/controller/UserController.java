package com.backend.controller;

import com.backend.entity.User;
import com.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Api(tags = "demo")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getUser(){
        return userService.getUser();
    }
}
