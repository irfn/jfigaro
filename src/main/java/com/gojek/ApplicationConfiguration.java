package com.gojek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public abstract class ApplicationConfiguration {
    Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);
    abstract Object getValue(String name);

    public String getValueAsString(String name) {
        Object value = getValue(name);
        if (value == null) {
            logger.warn("Config with key %s was null", name);
            return "";
        }
        else return value.toString();
    }

    public Integer getValueAsInt(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 : Integer.valueOf(value);
    }

    public Double getValueAsDouble(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 : Double.valueOf( value);
    }

    public Long getValueAsLong(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 :Long.decode(value);
    }

    public Float getValueAsFloat(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 :Float.parseFloat(value);
    }

    public boolean getValueAsBoolean(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? false :Boolean.valueOf(value);
    }
}
