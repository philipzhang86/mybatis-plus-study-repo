package com.jmalltech.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jmalltech.mapper.UserMapper;
import com.jmalltech.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("lambda")
@RestController
public class LambdaQueryController {
    @Autowired
    private UserMapper mapper;

    @GetMapping("test1")
    public List<User> test01() {
        //查询用户名包含a like，年龄在20到30之间，并且邮箱不为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //链式调用
        queryWrapper.like("name", "a").between("age", 20, 30)
                .isNotNull("email");

        LambdaQueryWrapper<User> lq = new LambdaQueryWrapper<>();
        lq.like(User::getUsername, "a")
                .between(User::getAge, 20, 30)
                .isNotNull(User::getEmail);
        List<User> list = mapper.selectList(lq);
        list.forEach(System.out::println);

        return list;
    }

    //QueryWrapper修改 【条件】
    //1.准备要修改的实体类数据
    //2.不能改为null

    //UpdateWrapper修改 【条件，修改】
    //1.直接携带修改数据 set("列名","值")
    //2.指定任意修改值 set("列明",null)
    @GetMapping("test2")
    public List<User> test2() {
        LambdaUpdateWrapper<User> lu = new LambdaUpdateWrapper<>();
        lu.eq(User::getEmail, "test10@study.com");
        List<User> list = mapper.selectList(lu);
        list.forEach(System.out::println);
        return list;
    }
}
