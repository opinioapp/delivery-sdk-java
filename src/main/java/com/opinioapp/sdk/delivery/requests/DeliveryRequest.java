package com.opinioapp.sdk.delivery.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;

/**
 * Created by lokesh on 4/11/15.
 */

public class DeliveryRequest {
    @JsonProperty("order_code")
    private String orderCode;

    @NotNull @Size(min = 1)
    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("merchant_phone")
    private String merchantPhone;

    @NotNull @Min(0)
    @JsonProperty("amount")
    private double amount;

    @NotNull @Min(0)
    @JsonProperty("amount_to_pay")
    private double amountToPay;

    @NotNull
    @JsonProperty("phone")
    private String customerPhone;

    @NotNull
    @JsonProperty("address")
    private String customerAddress;

    @NotNull
    @JsonProperty("locality")
    private String customerLocality;

    @JsonProperty("latitude")
    private double customerLatitude;

    @JsonProperty("longitude")
    private double customerLongitude;

    @JsonProperty("callback_url")
    private URL callbackUrl;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerLocality() {
        return customerLocality;
    }

    public void setCustomerLocality(String customerLocality) {
        this.customerLocality = customerLocality;
    }

    public double getCustomerLatitude() {
        return customerLatitude;
    }

    public void setCustomerLatitude(double customerLatitude) {
        this.customerLatitude = customerLatitude;
    }

    public double getCustomerLongitude() {
        return customerLongitude;
    }

    public void setCustomerLongitude(double customerLongitude) {
        this.customerLongitude = customerLongitude;
    }

    public URL getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(URL callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
