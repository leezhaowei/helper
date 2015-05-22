package com.zwli.clasz;

public class ParentClass {

    private String name;

    private String password;

    public ParentClass(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
