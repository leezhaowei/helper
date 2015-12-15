package com.zwli.research.reflect;

public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("com.zwli.reflect.PrintA");
            IPrint obj = (IPrint) c.newInstance();
            obj.print();

            c = Class.forName("com.zwli.reflect.PrintB");
            obj = (IPrint) c.newInstance();
            obj.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
