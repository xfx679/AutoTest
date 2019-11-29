package com.course.model;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer isDelete;

    //json的处理
//    @Override
//    public String toString(){
//        return (
//                "{"+
//                        "id:"+id+","+
//                        "username:"+username+","+
//                        "password:"+password+","+
//                        "age:"+age+","+
//                        "sex:"+sex+","+
//                        "permission:"+permission+","+
//                        "isDelete:"+isDelete+
//                        "}"
//        );
//    }
}
