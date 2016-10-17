package com.gojek;

import java.util.Set;

public class MissingRequiredConfigurationException extends RuntimeException {
    public MissingRequiredConfigurationException(Set<String> missingConfigurationNames) {
        super("Missing required configurations: " + missingConfigurationNames);
    }
}
