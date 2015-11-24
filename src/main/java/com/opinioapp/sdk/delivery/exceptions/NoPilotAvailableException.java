package com.opinioapp.sdk.delivery.exceptions;

/**
 * Created by lokesh on 5/11/15.
 */
public class NoPilotAvailableException extends Exception {
    public NoPilotAvailableException() {
        super("No Ground Pilot available");
    }
}
