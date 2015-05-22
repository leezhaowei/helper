package com.zwli.varargs;

public class TargetClass {

    public void callDoSomething() {
        doSomething();
        doSomething("a", "b", "c");
    }

    public void doSomething(String... values) {
        if (null == values || values.length == 0) {
            log("???");
            return;
        }
        for (String v : values) {
            log("--- " + v);
        }
    }

    public void doSomething() {
        log("$$$");
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
