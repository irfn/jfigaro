package com.gojek;

import java.util.Map;

/**
 * Meant for testing and needs to be directly instantiated.
 */
public class TestConfiguration extends ApplicationConfiguration {
    private Map<String, String> testConfig;

    public TestConfiguration(Map<String, String> testConfig) {
        this.testConfig = testConfig;
    }

    @Override
    Object getValue(String name) {
        return this.testConfig.get(name);
    }
}
