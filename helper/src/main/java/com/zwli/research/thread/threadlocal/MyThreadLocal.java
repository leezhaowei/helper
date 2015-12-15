package com.zwli.research.thread.threadlocal;

public class MyThreadLocal {

    public static final ThreadLocal<Context> _threadLocal = new ThreadLocal<Context>();

    public static void set(Context context) {
        _threadLocal.set(context);
    }

    public static void unset() {
        _threadLocal.remove();
    }

    public static Context get() {
        return _threadLocal.get();
    }
}
