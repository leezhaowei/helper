package com.zwli.research.thread.threadlocal;

public class BusinessService {

    public void businessMethod() {
        Context context = MyThreadLocal.get();
        System.out.println(context.getTransactionId());
    }

    public void businessMethodBaseOnNormalCache() {
        Context context = MyNormalCache.get();
        System.out.println(context.getTransactionId());
    }
}
