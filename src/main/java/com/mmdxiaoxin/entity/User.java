package com.mmdxiaoxin.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String passwd;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }

}
