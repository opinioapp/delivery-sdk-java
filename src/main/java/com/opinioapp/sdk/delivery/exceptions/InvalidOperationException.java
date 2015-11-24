package com.opinioapp.sdk.delivery.exceptions;

/**
 * Created by lokesh on 3/11/15.
 */
public class InvalidOperationException extends Exception {
    public InvalidOperationException() {
        super("Opinio delivery sdk: invalid operation attempted");
    }

    public InvalidOperationException(String s) {
        super(s);
    }
}
