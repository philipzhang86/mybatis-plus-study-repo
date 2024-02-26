package com.jmalltech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jmalltech.pojo.User;
import org.apache.ibatis.annotations.Param;

/*
 * mybatis: 1.mapper接口 curd方法  2. mapperxml curd sql语句
 * mybatis-plus: 1. mapper接口 extends BaseMapper  2. curd方法 curd的sql
 * */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> queryByAge(IPage<User> page, @Param("age") Integer age);
}
