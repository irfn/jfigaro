package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

class YamlConfiguration extends AbstractConfiguration {

    private Logger logger = LoggerFactory.getLogger(YamlConfiguration.class);
    private Map<String, Object> configuration;
    private String env;

    @SuppressWarnings("unchecked")
    YamlConfiguration(String env, String yamlFileName) throws FileNotFoundException {
        this.env = env;
        logger.debug("loading resource {} for {}", yamlFileName, this.env);
        URL resource = getClass().getResource(yamlFileName);
        Yaml yaml = new Yaml();
        FileInputStream fileInputStream = new FileInputStream(resource.getFile());
        configuration = (Map<String, Object>) yaml.load(fileInputStream);

    }

    @SuppressWarnings("unchecked")
    public Object getValue(String name) {
        String envConfigValue = System.getenv(name);
        if(envConfigValue != null) {
            return envConfigValue;
        } else {
            return Objects.equals(this.env, "development") ? defaultConfigurationValue(name) : overriddenConfigValue(name);
        }
    }

    private Object overriddenConfigValue(String name) {
        Map<String, Object> subConfig = overriddenConfig();
        if (subConfig == null) {
            return defaultConfigurationValue(name);
        } else {
            Object specificValue = subConfig.get(name);
            return specificValue != null ? specificValue : defaultConfigurationValue(name);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> overriddenConfig() {
        return (Map<String, Object>) configuration.get(this.env);
    }

    private Object defaultConfigurationValue(String configurationName) {
        return configuration.get(configurationName);
    }
}