package com.opinioapp.sdk.delivery.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by lokesh on 5/11/15.
 */
public class SupportedLocalities {
    @JsonProperty("locations")
    private String[] localities;

    public SupportedLocalities() {
    }

    public String[] getLocalities() {
        return localities;
    }

    public void setLocalities(String[] localities) {
        this.localities = localities;
    }

    @Override
    public String toString() {
        return "SupportedLocalities{" +
                "localities=" + Arrays.toString(localities) +
                '}';
    }
}
