package com.jmalltech.controller;

import com.jmalltech.pojo.User;
import com.jmalltech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userinfo")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("user")
    public User insertUser(@RequestBody User user) {
        if (userService.insert(user) > 0) {
            return user;
        }
        return null;
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userService.deleteById(id) > 0) {
            return "success";
        }
        return "fail";
    }

    @DeleteMapping("users")
    public String deleteUsers(@RequestBody List<Long> ids) {
        if (userService.deleteByIds(ids) > 0) {
            return "success";
        }
        return "fail";
    }

    @DeleteMapping("user")
    public String deleteByMap(@RequestParam String name, @RequestParam int age) {
        Map<String, Object> params = Map.of("name", name, "age", age);
        if (userService.deleteByMap(params) > 0) {
            return "success";
        }
        return "fail";
    }

    @PutMapping("user")
    public String updateUser(@RequestBody User user) {
        if (userService.updateById(user) > 0) {
            return "success";
        }
        return "fail";
    }

    @PostMapping("users")
    public List<User> findUsesByList(@RequestBody List<Long> list) {
        return userService.findByIDList(list);
    }
}
