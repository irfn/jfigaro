package com.gojek;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationConfigurationTest {

    private ApplicationConfiguration exampleConfiguration;

    @Test
    public void shouldGetValueAsStringViaCast() {
        exampleConfiguration = new ApplicationConfiguration() {
            public Object getValue(String name) {
                return "1.0";
            }
        };
        assertEquals("1.0", exampleConfiguration.getValueAsString("test"));
    }

    @Test
    public void shouldGetValueAsInt() {
        exampleConfiguration = new ApplicationConfiguration() {
            public Object getValue(String name) {
                return "1";
            }
        };
        assertEquals(Integer.valueOf("1"), exampleConfiguration.getValueAsInt("test"));
    }

    @Test
    public void shouldGetValueAsDouble() {
        exampleConfiguration = new ApplicationConfiguration() {
            public Object getValue(String name) {
                return "1.0";
            }
        };
        assertEquals(Double.valueOf("1.0"), exampleConfiguration.getValueAsDouble("test"));
    }

    @Test
    public void shouldGetValueAsLong() {
        exampleConfiguration = new ApplicationConfiguration() {
            public Object getValue(String name) {
                return "1";
            }
        };
        assertEquals(Long.valueOf(1), exampleConfiguration.getValueAsLong("test"));
    }

    @Test
    public void shouldGetValueAsFloat() {
        exampleConfiguration = new ApplicationConfiguration() {
            public Object getValue(String name) {
                return "1";
            }
        };
        assertEquals(Float.valueOf(1), exampleConfiguration.getValueAsFloat("test"));
    }
}