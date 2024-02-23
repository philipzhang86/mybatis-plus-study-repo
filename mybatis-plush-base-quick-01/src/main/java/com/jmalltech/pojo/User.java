package com.jmalltech.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("user")
@Data
public class User {
    private Long id;
    private String username;
    private Integer age;
    private String email;
}
