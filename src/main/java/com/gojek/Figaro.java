package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.Optional;

public class Figaro {
    public static ApplicationConfiguration configure() {
        Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

        String appEnvironment = System.getenv("APP_ENVIRONMENT");
        if (appEnvironment == null || appEnvironment.equals("test") || appEnvironment.equals("development")) {
            if (appEnvironment == null) {
                appEnvironment = "";
            }
            logger.info("Loading file based configuration");
            try {
                String yamlFileName = getYamlFileName();
                return new YamlConfiguration(appEnvironment, yamlFileName);
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

    private static String getYamlFileName() {
        Optional<String> yamlFilename = Optional.ofNullable(System.getProperty("figaro.yaml.filename"));
        if (yamlFilename.isPresent()) {
            return yamlFilename.get();
        }
        return "/application.yml";
    }
}
