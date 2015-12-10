package com.zwli.datastructure.example;

public class DsHandler {

    private static DsHandler instance;

    public static DsHandler getInstance() {
        if (instance == null) {
            synchronized (DsHandler.class) {
                if (instance == null) {
                    instance = new DsHandler();
                }
            }
        }
        return instance;
    }

    private DsHandler() {
    }

}
