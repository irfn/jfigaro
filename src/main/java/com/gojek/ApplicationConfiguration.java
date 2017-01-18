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

    public String getValueAsString(String name, String defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsString(name) : defaultValue;
    }

    public Integer getValueAsInt(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 : Integer.valueOf(value);
    }

    public Integer getValueAsInt(String name, int defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsInt(name) : defaultValue;
    }

    public Double getValueAsDouble(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 : Double.valueOf( value);
    }

    public Double getValueAsDouble(String name, double defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsDouble(name) : defaultValue;
    }

    public Long getValueAsLong(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 :Long.decode(value);
    }

    public Long getValueAsLong(String name, long defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsLong(name) : defaultValue;
    }

    public Float getValueAsFloat(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 :Float.parseFloat(value);
    }

    public Float getValueAsFloat(String name, float defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsFloat(name) : defaultValue;
    }

    public boolean getValueAsBoolean(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? false :Boolean.valueOf(value);
    }

    public boolean getValueAsBoolean(String name, boolean defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsBoolean(name) : defaultValue;
    }
}
