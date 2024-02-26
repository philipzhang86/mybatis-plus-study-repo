package com.jmalltech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmalltech.pojo.User;

/*
* mybatis: 1.mapper接口 curd方法  2. mapperxml curd sql语句
* mybatis-plus: 1. mapper接口 extends BaseMapper  2. curd方法 curd的sql
* */
public interface UserMapper extends BaseMapper<User> {

}
