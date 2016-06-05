package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EnvironmentConfiguration extends AbstractConfiguration {
    private Logger logger = LoggerFactory.getLogger(EnvironmentConfiguration.class);

    public EnvironmentConfiguration() {
        logger.debug("loading encironment configuration");
    }

    @Override
    public Object getValue(String name) {
        return System.getenv(name);
    }
}