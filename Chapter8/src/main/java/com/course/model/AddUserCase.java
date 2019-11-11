package com.course.model;

import lombok.Data;

@Data
public class AddUserCase {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer isDelete;
    private String expected;
}
