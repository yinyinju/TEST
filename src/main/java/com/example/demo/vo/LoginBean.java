package com.example.demo.vo;

import java.util.List;

public class LoginBean {
    private String name;

    private String password;

    private List<String> roleName;

    private List<String> menuName;

    public LoginBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginBean()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
