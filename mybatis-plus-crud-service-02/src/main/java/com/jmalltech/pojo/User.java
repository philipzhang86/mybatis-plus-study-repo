package com.jmalltech.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName(value = "user",schema = "mybatis_demo01")//No schema means cant find the table
@Data// entity class must choose Object type as data type, primitive type has default value, which will cause the
// problem of not being able to judge whether the field is empty
public class User {
    @TableId
    private Long id;
    @TableField("name")
    private String username;
    private Integer age;
    private String email;
}
