package com.opinioapp.sdk.delivery.reponses;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by lokesh on 5/11/15.
 */
public enum  DeliveryStateEnum {
    ASSIGNED(0),
    ACCEPTED(1),
    PICKUP_STARTED(2),
    PICKUP_ARRIVED(3),
    DROP_STARTED(4),
    DROP_ARRIVED(5),
    DROP_FINISHED(6),
    RETURN_STARTED(7),
    RETURNED(8),
    CANCELLED(9),;

    private int value;
    private static DeliveryStateEnum[] values;

    DeliveryStateEnum(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return this.value;
    }

    public static DeliveryStateEnum[] deliveryEnums() {
        if (values == null) {
            values = DeliveryStateEnum.values();
        }

        return values;
    }
}
