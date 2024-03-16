package com.example.android_homeword_1;

public class userinfo {

    private String name;
    private String password;


    public userinfo(String name,String password){
        this.name=name;
        this.password=password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
