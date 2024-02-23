package com.jmalltech.controller;

import com.jmalltech.pojo.User;
import com.jmalltech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userinfo")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public List<User> findAll() {
        return userService.findAll();
    }
}
