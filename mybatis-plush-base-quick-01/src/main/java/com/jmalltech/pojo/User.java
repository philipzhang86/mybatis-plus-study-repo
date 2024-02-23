package com.jmalltech.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("user")
@Data
public class User {
    @TableId
    private Long id;
    private String username;
    private Integer age;
    private String email;
}
