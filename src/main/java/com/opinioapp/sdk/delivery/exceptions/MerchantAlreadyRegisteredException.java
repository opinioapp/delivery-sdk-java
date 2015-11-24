package com.opinioapp.sdk.delivery.exceptions;

/**
 * Created by lokesh on 5/11/15.
 */
public class MerchantAlreadyRegisteredException extends Exception {
    public MerchantAlreadyRegisteredException() {
        super("Merchant is already registered with the app");
    }
}
