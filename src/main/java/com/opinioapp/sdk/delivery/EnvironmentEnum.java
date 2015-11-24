package com.opinioapp.sdk.delivery;

/**
 * Created by lokesh on 4/11/15.
 */
public enum  EnvironmentEnum {
    LOCALHOST("http://localhost:8000"),
    STAGING("http://staging.deliver.opinioapp.com"),
    TEST("http://test.deliver.opinioapp.com"),
    PRODUCTION("http://deliver.opinioapp.com");

    private String value;
    private static EnvironmentEnum[] values;

    EnvironmentEnum(String env) {
        this.value = env;
    }

    public String getValue() {
        return this.value;
    }

    public static EnvironmentEnum[] environments() {
        if (values == null) {
            values = EnvironmentEnum.values();
        }

        return values;
    }
}
