package com.opinioapp.sdk.delivery.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by lokesh on 4/11/15.
 */
public class MerchantRegistrationRequest {
    @NotBlank
    private String name;
    @Size(min = 10, max = 10)
    private String phone;
    @NotBlank
    private String address;
    @Email
    private String email;
    @Min(0)
    private double latitude;
    @Min(0)
    private double longitude;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("app_merchant_id")
    private String appMerchantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAppMerchantId() {
        return appMerchantId;
    }

    public void setAppMerchantId(String appMerchantId) {
        this.appMerchantId = appMerchantId;
    }

    @Override
    public String toString() {
        return "MerchantRegistrationRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", appMerchantId='" + appMerchantId + '\'' +
                '}';
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
