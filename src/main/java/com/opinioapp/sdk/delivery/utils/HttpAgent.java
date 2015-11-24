package com.opinioapp.sdk.delivery.utils;

import com.opinioapp.sdk.delivery.reponses.ApiError;
import com.opinioapp.sdk.delivery.reponses.BaseApiResponse;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by lokesh on 4/11/15.
 */
public class HttpAgent {

    public enum HTTPMethod {
        GET, POST, PUT;
    }

    private static final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    private static ResponseHandler<BaseApiResponse> responseHandler = response -> {
        BaseApiResponse baseApiResponse = new BaseApiResponse(
                response.getStatusLine().getStatusCode(),
                EntityUtils.toString(response.getEntity()),
                response.getAllHeaders()
        );

        Header contentTypeHeader = response.getFirstHeader(HttpHeaders.CONTENT_TYPE);
        String contentType = contentTypeHeader != null ? contentTypeHeader.getValue() : ContentType.DEFAULT_TEXT.getMimeType();

        if (contentType.equals(ContentType.APPLICATION_JSON.getMimeType())) {
            baseApiResponse.setJsonBody(baseApiResponse.getRawBody());
        } else {
            ApiError apiError = new ApiError();
            apiError.setStatusCode(response.getStatusLine().getStatusCode());
            apiError.setMessage(baseApiResponse.getRawBody());
            apiError.setError("opinio api encountered an unexpected error");

            baseApiResponse.setJsonBody(JSON.stringify(apiError));
        }

        return baseApiResponse;
    };

    public static BaseApiResponse request(HTTPMethod method, String url, String body, String authorizationHeader) throws IOException {
        BaseApiResponse response = null;
        switch (method) {
            case GET:
                url += "?" + body;
                response =  get(url, authorizationHeader);
                break;
            case POST:
                response =  post(url, body, authorizationHeader);
                break;
            case PUT:
                response =  put(url, body, authorizationHeader);
                break;
        }
        return response;
    }

    /**
     * makes a get request to a url
     *
     * @param url URL
     * @return String response from the url
     */
    public static BaseApiResponse get(String url, String authorizationHeader) throws IOException {
        BaseApiResponse requestResponse;

        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        requestResponse = httpClient.execute(httpGet, responseHandler);

        return requestResponse;

    }

    /**
     * makes a post request to a url with some data
     *
     * @param url  String url
     * @param body post body
     * @return String response of the post request
     */

    public static BaseApiResponse post(String url, String body, String authorizationHeader) throws IOException {

        BaseApiResponse requestResponse;

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED));
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        requestResponse = httpClient.execute(httpPost, responseHandler);

        return requestResponse;
    }

    /**
     *  make a put request
     * @param url String url
     * @param body String request payload
     * @param authorizationHeader String Authorization header
     * @return BaseApiResponse response from url
     * @throws IOException
     */
    public static BaseApiResponse put(String url, String body, String authorizationHeader) throws IOException {
        BaseApiResponse requestResponse;

        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED));
        httpPut.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        requestResponse = httpClient.execute(httpPut, responseHandler);

        return requestResponse;
    }

}
