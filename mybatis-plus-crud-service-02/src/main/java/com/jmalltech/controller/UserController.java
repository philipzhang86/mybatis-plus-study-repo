package com.jmalltech.controller;

import com.jmalltech.mapper.UserMapper;
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
    private UserService userServiceImpl;

    @Autowired
    private UserMapper mapper;

    @GetMapping("list")
    public List<User> findAll() {
        List<User> list = userServiceImpl.list();
        for (User u : list) {
            System.out.println(u);
        }
        return list;
    }

    @PostMapping("user")
    public User insertUser(@RequestBody User user) {
        if (userServiceImpl.save(user)) return user;
        return null;
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userServiceImpl.removeById(id)) return "success";
        return "fail";
    }

    @DeleteMapping("users")
    public String deleteUsers(@RequestBody List<Long> ids) {
        if (userServiceImpl.removeByIds(ids)) return "success";
        return "fail";
    }

    @DeleteMapping("user")
    public String deleteByMap(@RequestParam String name, @RequestParam int age) {
        Map<String, Object> params = Map.of("name", name, "age", age);
        if (userServiceImpl.removeByMap(params)) return "success";
        return "fail";
    }

    @PutMapping("user")
    public String updateUser(@RequestBody User user) {
        if (userServiceImpl.updateById(user)) return "success";
        return "fail";
    }

    @PostMapping("users")
    public List<User> findUsesByList(@RequestBody List<Long> list) {
        return userServiceImpl.listByIds(list);
    }
}
