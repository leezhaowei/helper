package com.zwli.research.thread.threadlocal;

public class Context {

    private String transactionId;

    @Override
    public String toString() {
        return "Context [transactionId=" + transactionId + "]";
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
