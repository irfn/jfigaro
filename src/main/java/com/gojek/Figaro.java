package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Figaro {
    public static ApplicationConfiguration configure(Set<String> requiredKeys) throws MissingKeysException {
        Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

        String appEnvironment = System.getenv("APP_ENVIRONMENT");
        ApplicationConfiguration configuration = null;
        if (appEnvironment == null || appEnvironment.equals("test") || appEnvironment.equals("development")) {
            if (appEnvironment == null) {
                appEnvironment = "";
            }
            logger.info("Loading file based configuration");
            try {
                String yamlFileName = getYamlFileName();
                configuration = new YamlConfiguration(appEnvironment, yamlFileName);
            } catch (FileNotFoundException e) {
                logger.error("yaml configuration was not found", e);
                e.printStackTrace();
                return null;
            }
        } else {
            logger.info("Loading environment based configuration");
            configuration = new EnvironmentConfiguration();
        }
        Set<String> missingKeys = getMissingKeys(configuration, requiredKeys);
        if(!missingKeys.isEmpty()) {
            throw new MissingKeysException(missingKeys);
        }
        return configuration;
    }

    private static String getYamlFileName() {
        Optional<String> yamlFilename = Optional.ofNullable(System.getProperty("figaro.yaml.filename"));
        if (yamlFilename.isPresent()) {
            return yamlFilename.get();
        }
        return "/application.yml";
    }

    private static Set<String> getMissingKeys(ApplicationConfiguration configuration, Set<String> requiredKeys) {
        Set<String> missingKeys = new HashSet<>();
        if(configuration != null && requiredKeys != null) {
            for(String requiredKey: requiredKeys) {
                Object requiredValue = configuration.getValue(requiredKey);
                if(requiredValue == null) {
                    missingKeys.add(requiredKey);
                }
            }
        }
        return  missingKeys;
    }
}
