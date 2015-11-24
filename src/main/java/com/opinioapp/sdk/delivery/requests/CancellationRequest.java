package com.opinioapp.sdk.delivery.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lokesh on 4/11/15.
 */
public class CancellationRequest {

    @NotBlank @JsonProperty("order_code")
    private String orderCode;

    @NotBlank @JsonProperty("cancel_reason")
    private String reasonForCancellation;

    public CancellationRequest() {
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getReasonForCancellation() {
        return reasonForCancellation;
    }

    public void setReasonForCancellation(String reasonForCancellation) {
        this.reasonForCancellation = reasonForCancellation;
    }

    @Override
    public String toString() {
        return "CancellationRequest{" +
                "orderCode='" + orderCode + '\'' +
                ", reasonForCancellation='" + reasonForCancellation + '\'' +
                '}';
    }
}
