package com.opinioapp.sdk.delivery.exceptions;

/**
 * Created by lokesh on 3/11/15.
 */
public class KeysNotFoundException extends Exception {
    public KeysNotFoundException() {
        super("Opinio delivery sdk: api accessKey and/or secret not provided");
    }

    public KeysNotFoundException(String s) {
        super(s);
    }
}
