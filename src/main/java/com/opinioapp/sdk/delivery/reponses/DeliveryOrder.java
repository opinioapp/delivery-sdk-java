package com.opinioapp.sdk.delivery.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lokesh on 5/11/15.
 */
public class DeliveryOrder {

    @JsonProperty("amount")
    private double amount;
    @JsonProperty("amount_to_pay")
    private double amountToPay;
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
    @JsonProperty("otpList")
    private List<Otp> otpList;
    @JsonProperty("merchant")
    private String merchant;
    @JsonProperty("customer_phone")
    private String customerPhone;
    @JsonProperty("customer_address")
    private String customerAddress;

    public DeliveryOrder() {
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

    public void setOrderState(DeliveryStateEnum orderState) {
        this.orderState = orderState;
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
                ", amount='" + amount + '\'' +
                ", amount_to_pay='" + amountToPay + '\'' +
                ", pilotName='" + pilotName + '\'' +
                ", pilotPhone='" + pilotPhone + '\'' +
                ", pilotLatitude='" + pilotLatitude + '\'' +
                ", pilotLongitude='" + pilotLongitude + '\'' +
                ", eta='" + eta + '\'' +
                ", deliveryEta='" + deliveryEta + '\'' +
                '}';
    }

    public List<Otp> getOtpList() {
        return otpList;
    }

    public void setOtpList(List<Otp> otpList) {
        this.otpList = otpList;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
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

    public static class Otp{
        @JsonProperty("orderNo")
        private int orderNo;
        @JsonProperty("otp")
        private int otp;

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public int getOtp() {
            return otp;
        }

        public void setOtp(int otp) {
            this.otp = otp;
        }
    }
}
