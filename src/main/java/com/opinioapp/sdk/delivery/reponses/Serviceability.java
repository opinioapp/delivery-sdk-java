package com.opinioapp.sdk.delivery.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lokesh on 5/11/15.
 */
public class Serviceability {
    @JsonProperty("GP_availability")
    private int gpAvailabilty;
    @JsonProperty("eta")
    private String etaInSeconds;
    @JsonProperty("pilot_latitude")
    private double pilotLatitude;
    @JsonProperty("pilot_longitude")
    private double pilotLongitude;
    @JsonProperty("merchant_latitude")
    private double merchantLatitude;
    @JsonProperty("merchant_longitude")
    private double merchantLongitude;
    @JsonProperty("pickup_distance")
    private int pickupDistance;
    @JsonProperty("etd")
    private int etd;
    @JsonProperty("drop_distance")
    private int dropDistance;

    public Serviceability() {
    }

    public int getGpAvailabilty() {
        return gpAvailabilty;
    }

    public void setGpAvailabilty(int gpAvailabilty) {
        this.gpAvailabilty = gpAvailabilty;
    }

    public String getEtaInSeconds() {
        return etaInSeconds;
    }

    public void setEtaInSeconds(String etaInSeconds) {
        this.etaInSeconds = etaInSeconds;
    }

    public boolean isGpAvailable() {
        return this.gpAvailabilty == 1;
    }

    public double getPilotLatitude() {
        return pilotLatitude;
    }

    public void setPilotLatitude(double pilotLatitude) {
        this.pilotLatitude = pilotLatitude;
    }

    public double getPilotLongitude() {
        return pilotLongitude;
    }

    public void setPilotLongitude(double pilotLongitude) {
        this.pilotLongitude = pilotLongitude;
    }

    public double getMerchantLatitude() {
        return merchantLatitude;
    }

    public void setMerchantLatitude(double merchantLatitude) {
        this.merchantLatitude = merchantLatitude;
    }

    public double getMerchantLongitude() {
        return merchantLongitude;
    }

    public void setMerchantLongitude(double merchantLongitude) {
        this.merchantLongitude = merchantLongitude;
    }

    public int getPickupDistance() {
        return pickupDistance;
    }

    public void setPickupDistance(int pickupDistance) {
        this.pickupDistance = pickupDistance;
    }

    public int getEtd() {
        return etd;
    }

    public void setEtd(int etd) {
        this.etd = etd;
    }

    public int getDropDistance() {
        return dropDistance;
    }

    public void setDropDistance(int dropDistance) {
        this.dropDistance = dropDistance;
    }

    @Override
    public String toString() {
        return "Serviceability{" +
                "gpAvailabilty=" + gpAvailabilty +
                ", etaInSeconds=" + etaInSeconds +
                '}';
    }
}
