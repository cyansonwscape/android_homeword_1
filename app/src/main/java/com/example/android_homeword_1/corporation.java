package com.example.android_homeword_1;

public class corporation {

    private int id;
    private String name;



    public corporation(int id,String name){
        this.name=name;
        this.id=id;
            }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
