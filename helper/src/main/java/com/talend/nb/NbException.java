package com.talend.nb;

public class NbException extends Exception {

    private static final long serialVersionUID = -2120144760326255212L;

    public NbException() {
        super();
    }

    public NbException(String message, Throwable cause) {
        super(message, cause);
    }

    public NbException(String message) {
        super(message);
    }

    public NbException(Throwable cause) {
        super(cause);
    }

}
