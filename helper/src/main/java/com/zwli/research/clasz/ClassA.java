package com.zwli.research.clasz;

public class ClassA extends ParentClass {

    private String label;

    public ClassA(String name, String password) {
        super(name, password);
    }

    public ClassA(String name, String password, String label) {
        super(name, password);
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
