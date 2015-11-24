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

    @Override
    public String toString() {
        return "Serviceability{" +
                "gpAvailabilty=" + gpAvailabilty +
                ", etaInSeconds=" + etaInSeconds +
                '}';
    }
}
