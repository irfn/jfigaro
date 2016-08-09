package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EnvironmentConfiguration extends ApplicationConfiguration {
    private Logger logger = LoggerFactory.getLogger(EnvironmentConfiguration.class);

    public EnvironmentConfiguration() {
        logger.debug("loading environment configuration");
    }

    @Override
    public Object getValue(String name) {
        return System.getenv(name);
    }
}