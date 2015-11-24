package com.opinioapp.sdk.delivery.reponses;

import org.apache.http.Header;
import org.apache.http.HttpStatus;

import java.util.Arrays;

/**
 * Created by lokesh on 4/11/15.
 */
public class BaseApiResponse {

    int statusCode;
    String rawBody;
    Header[] headers;
    String jsonBody;

    public BaseApiResponse() {
    }

    public BaseApiResponse(int statusCode, String rawBody, Header[] headers) {
        this.statusCode = statusCode;
        this.rawBody = rawBody;
        this.headers = headers;
    }

    public String getRawBody() {
        return rawBody;
    }

    public void setRawBody(String rawBody) {
        this.rawBody = rawBody;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public boolean isOk() {
        return this.statusCode < HttpStatus.SC_MULTIPLE_CHOICES;
    }

    @Override
    public String toString() {
        return "BaseApiResponse{" +
                "statusCode=" + statusCode +
                ", rawBody='" + rawBody + '\'' +
                ", jsonBody='" + jsonBody + '\'' +
                ", headers=" + Arrays.toString(headers) +
                '}';
    }

}
