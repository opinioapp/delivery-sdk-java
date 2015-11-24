package com.opinioapp.sdk.delivery.reponses;

/**
 * Created by lokesh on 4/11/15.
 */
public class ApiError {

    private String error;
    private String message;
    private int statusCode;

    public ApiError() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
