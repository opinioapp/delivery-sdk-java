package com.opinioapp.sdk.delivery.exceptions;

/**
 * Created by lokesh on 3/11/15.
 */
public class PluginNotSetupException extends Exception {
    public PluginNotSetupException() {
        super("Opinio delivery sdk setup not completed properly");
    }

    public PluginNotSetupException(String s) {
        super(s);
    }
}
