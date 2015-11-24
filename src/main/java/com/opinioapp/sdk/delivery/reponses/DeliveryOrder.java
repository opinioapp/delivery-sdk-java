package com.opinioapp.sdk.delivery.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lokesh on 5/11/15.
 */
public class DeliveryOrder {
    @JsonProperty("pilot_name")
    private String pilotName;
    @JsonProperty("pilot_phone")
    private String pilotPhone;
    @JsonProperty("pilot_latitude")
    private String pilotLatitude;
    @JsonProperty("pilot_longitude")
    private String pilotLongitude;
    @JsonProperty("order_code")
    private String orderCode;
    @JsonProperty("state")
    private DeliveryStateEnum orderState;
    @JsonProperty("eta")
    private String eta;
    @JsonProperty("delivery_eta")
    private String deliveryEta;

    public DeliveryOrder() {
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getPilotPhone() {
        return pilotPhone;
    }

    public void setPilotPhone(String pilotPhone) {
        this.pilotPhone = pilotPhone;
    }

    public String getPilotLatitude() {
        return pilotLatitude;
    }

    public void setPilotLatitude(String pilotLatitude) {
        this.pilotLatitude = pilotLatitude;
    }

    public String getPilotLongitude() {
        return pilotLongitude;
    }

    public void setPilotLongitude(String pilotLongitude) {
        this.pilotLongitude = pilotLongitude;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderState() {
        return orderState.getValue();
    }

    public void setOrderState(int orderState) {
        this.orderState = DeliveryStateEnum.deliveryEnums()[orderState];
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getDeliveryEta() {
        return deliveryEta;
    }

    public void setDeliveryEta(String deliveryEta) {
        this.deliveryEta = deliveryEta;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "  orderCode='" + orderCode + '\'' +
                ", orderState='" + orderState + '\'' +
                ", pilotName='" + pilotName + '\'' +
                ", pilotPhone='" + pilotPhone + '\'' +
                ", pilotLatitude='" + pilotLatitude + '\'' +
                ", pilotLongitude='" + pilotLongitude + '\'' +
                ", eta='" + eta + '\'' +
                ", deliveryEta='" + deliveryEta + '\'' +
                '}';
    }
}
