package com.zwli.thread.threadlocal;

import java.util.ArrayList;
import java.util.List;


public class MyNormalCache {
    private static final List<Context> _list = new ArrayList<Context>();
    public static void set(Context context) {
        _list.add(context);
    }

    public static void unset() {
        _list.remove(0);
    }

    public static Context get() {
        return _list.get(0);
    }
}
