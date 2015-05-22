package com.zwli.reflect;

/**
 * created by zwli on Mar 31, 2014 Detailled comment
 */
public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("org.zwli.reflect.PrintA");
            IPrint obj = (IPrint) c.newInstance();
            obj.print();

            c = Class.forName("org.zwli.reflect.PrintB");
            obj = (IPrint) c.newInstance();
            obj.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
