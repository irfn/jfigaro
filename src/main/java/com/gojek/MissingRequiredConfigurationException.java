package com.gojek;

import java.util.Set;

public class MissingRequiredConfigurationException extends Throwable {
    public MissingRequiredConfigurationException(Set<String> missingConfigurationNames) {
        super("Missing required configurations: " + missingConfigurationNames);
    }
}
