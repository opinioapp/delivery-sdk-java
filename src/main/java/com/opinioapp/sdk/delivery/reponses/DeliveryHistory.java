package com.opinioapp.sdk.delivery.reponses;

import java.util.List;

/**
 * Created by lokesh on 5/11/15.
 */
public class DeliveryHistory {
    private List<DeliveryOrder> deliveries;

    public DeliveryHistory() {
    }

    public List<DeliveryOrder> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryOrder> deliveries) {
        this.deliveries = deliveries;
    }
}
