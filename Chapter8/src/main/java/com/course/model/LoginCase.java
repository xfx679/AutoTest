package com.course.model;

import lombok.Data;

@Data
public class LoginCase {

    private Integer id;
    private String username;
    private String password;
    private String expected;



}
