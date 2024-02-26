package com.jmalltech.service;

import com.jmalltech.mapper.UserMapper;
import com.jmalltech.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public List<User> findByIDList(List<Long> list) {
        List<User> userList = userMapper.selectBatchIds(list);
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    public int deleteByIds(List<Long> ids) {
        return userMapper.deleteBatchIds(ids);
    }

    public int deleteByMap(Map<String, Object> params) {
        return userMapper.deleteByMap(params);
    }

    public int updateById(User user) {
        return userMapper.updateById(user);
    }

}
