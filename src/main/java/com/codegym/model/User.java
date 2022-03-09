package com.codegym.model;

import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private Date birthday;
    private String address;
    private String email;

    public User() {
    }

    public User(int id, String username, String password, Date birthday, String address, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
    }

    public User(String username, String password, Date birthday, String address, String email) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
