package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class Figaro {
    public static AbstractConfiguration configure() {
        Logger logger = LoggerFactory.getLogger(AbstractConfiguration.class);

        String appEnvironment = System.getenv("APP_ENVIRONMENT");
        if (appEnvironment == null || appEnvironment.equals("test") || appEnvironment.equals("development")) {
            if (appEnvironment == null) {
                appEnvironment = "";
            }
            logger.info("Loading file based configuration");
            try {
                return new YamlConfiguration(appEnvironment, "/application.yml");
            } catch (FileNotFoundException e) {
                logger.error("yaml configuration was not found", e);
                e.printStackTrace();
                return null;
            }
        } else {
            logger.info("Loading environment based configuration");
            return new EnvironmentConfiguration();
        }
    }
}
