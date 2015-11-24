package com.opinioapp.sdk.delivery;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lokesh on 4/11/15.
 */
public class Credentials {
    @NotNull @Size(min = 1)
    private String accessKeyId;
    @NotNull @Size(min = 1)
    private String secretKey;

    public Credentials() {
    }

    public Credentials(String accessKeyId, String secretKey) {
        this.accessKeyId = accessKeyId;
        this.secretKey = secretKey;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
