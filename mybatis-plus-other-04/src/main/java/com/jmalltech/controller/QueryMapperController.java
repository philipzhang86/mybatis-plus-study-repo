package com.jmalltech.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jmalltech.mapper.UserMapper;
import com.jmalltech.pojo.User;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("querywrapper")
@RestController
public class QueryMapperController {
    @Autowired
    private UserMapper mapper;

    @GetMapping("test1")
    public List<User> test_01() {
        QueryWrapper<User> qw = new QueryWrapper<>();

        //select * from user where name like '%a%' and age >= 20 and age <= 30 and email is not null
        qw.like("name", "a").between("age", 20, 30).isNotNull("email");
        List<User> list = mapper.selectList(qw);
        list.forEach(System.out::println);
        return list;
    }

    @GetMapping("test2")
    public List<User> test_02() {
        ///按年龄降序查询用户，如果年龄相同则按id升序排列
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.orderByAsc("age").orderByDesc("id");
        return mapper.selectList(qw);
    }

    @DeleteMapping("test3")
    public String test_03() {
        //使用updateWrapper可以随意设置列的值！！  使用queryWrapper可以进行update修改，但是无法将列值修改为null值！
        UpdateWrapper<User> qw = new UpdateWrapper();
        qw.eq("email", "test7@study.com");
        mapper.delete(qw);
        return "success";
    }

    @GetMapping("test4")
    public String test_04() {
        //select * from user where name like '%a%' and age >= 22 or email is not null

        QueryWrapper<User> qw = new QueryWrapper();
        qw.like("name", "a")
                .ge("age", 20)//条件直接调用方法默认使用and 拼接
                .or().isNull("email");// or().第一个条件是or -> 拼接条件还是and
        List<User> list = mapper.selectList(qw);
        list.forEach(System.out::println);
        return "Success";
    }

    @GetMapping("test5")
    public List<User> test_05() {
        //mapper.selectList(null); //mapper.selectList(qurryWrapper == null)是查询全部列
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.gt("id", 1);
        qw.select("name", "age", "email");//这个可以用在DTO 返回由特定的Columns 组成的DTO
        return mapper.selectList(qw);
    }


    @GetMapping("test6")
    public List<User> test_06() {
        //前端传入了两个参数 name age
        //name不为空，作为条件 = 查询
        //age > 18 ，作为条件，查询等于 age =

        String name = "Jone";
        Integer age = 18;

        QueryWrapper<User> qw = new QueryWrapper<>();
        //判断条件拼接
        //当name不为null拼接等于, age > 1 拼接等于判断
        //方案1: 手动判断
        if (StringUtils.isNotBlank(name)) {
            qw.eq("name", name);
        }
        if (age != null && age > 18) {
            qw.eq("age", age);
        }

        /*
        //每个方法都会有一个 boolean condition,允许我们第一位放一个比较表达式 true 整个条件生效 false 不生效
        // if test ="判断"
        //gt(boolean condition, R column, Object val)
        qw.eq(StringUtils.isNotBlank(name),"name",name);
        qw.eq(age != null && age > 18,"age",age);
        * */
        return mapper.selectList(qw);
    }
}
