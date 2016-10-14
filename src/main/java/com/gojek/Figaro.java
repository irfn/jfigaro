package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Figaro {
    public static ApplicationConfiguration configure(Set<String> requiredConfigurationNames) throws MissingRequiredConfigurationException {
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
        Set<String> missingRequiredConfigurationNames = getMissingRequiredConfigurationNames(configuration, requiredConfigurationNames);
        if(!missingRequiredConfigurationNames.isEmpty()) {
            throw new MissingRequiredConfigurationException(missingRequiredConfigurationNames);
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

    private static Set<String> getMissingRequiredConfigurationNames(ApplicationConfiguration configuration, Set<String> requiredConfigurationsNames) {
        Set<String> missingRequiredConfigurations = new HashSet<>();
        if(configuration != null && requiredConfigurationsNames != null) {
            for(String requiredConfiguration: requiredConfigurationsNames) {
                Object requiredConfigurationValue = configuration.getValue(requiredConfiguration);
                if(requiredConfigurationValue == null) {
                    missingRequiredConfigurations.add(requiredConfiguration);
                }
            }
        }
        return  missingRequiredConfigurations;
    }
}
