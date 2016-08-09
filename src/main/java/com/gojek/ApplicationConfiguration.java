package com.gojek;

public abstract class ApplicationConfiguration {
    abstract Object getValue(String name);

    public String getValueAsString(String name) {
        return getValue(name).toString();
    }

    public Integer getValueAsInt(String name) {
        return (Integer) getValue(name);
    }

    public Double getValueAsDouble(String name) {
        return (Double) getValue(name);
    }

    public Long getValueAsLong(String name) {
        return Long.decode(this.getValueAsString(name));
    }

    public Float getValueAsFloat(String name) {
        return Float.parseFloat(this.getValueAsString(name));
    }
}
