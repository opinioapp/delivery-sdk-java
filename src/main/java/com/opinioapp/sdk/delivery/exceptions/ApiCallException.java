package com.opinioapp.sdk.delivery.exceptions;

import com.opinioapp.sdk.delivery.reponses.ApiError;

/**
 * Created by lokesh on 5/11/15.
 */
public class ApiCallException extends Exception {
    private final ApiError error;

    public ApiCallException(ApiError apiError) {
        this.error = apiError;
    }

    public ApiError getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ApiCallException{" +
                "error=" + error +
                '}';
    }
}
