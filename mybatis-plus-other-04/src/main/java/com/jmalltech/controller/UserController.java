package com.jmalltech.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


    @GetMapping("pages_1")
    public String findUsersByPage() {
        //设置分页参数 获取一个page obj 告诉MP 怎么分页
        //           此page obj  是    第几页， 每页的size
        Page<User> page = new Page<>(1, 5);
        mapper.selectPage(page, null);//queryWrapper是啥？
        //获取分页数据
        List<User> list = page.getRecords();//当前页的数据
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());//页码
        System.out.println("每页显示的条数：" + page.getSize());//页容量
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());//总条数
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
        return "success";
    }


    @GetMapping("pages_2")
    public String findUserByAgeWithPage(){
        Page<User> page = new Page<>(2,3);
        mapper.queryByAge(page,1);

        long current = page.getCurrent(); //页码
        System.out.println("current = " + current);
        long size = page.getSize(); //页容量
        System.out.println("size = " + size);
        List<User> records = page.getRecords(); //当前页的数据
        System.out.println("records = " + records);
        long total = page.getTotal(); //总条数
        System.out.println("total = " + total);
        return "success";
    }
}
