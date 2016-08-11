package com.gojek;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestConfigurationTest {

    @Test
    public void shouldBeConfiguredViaAMapOfKeysAndValues() {
        Map<String, String> map = new HashMap<>();
        map.put("test1", "testValue1");
        map.put("test2", "testValue2");
        ApplicationConfiguration testConfiguration = new TestConfiguration(map);
        assertEquals(map.get("test1"), testConfiguration.getValueAsString("test1"));
        assertEquals(map.get("test2"), testConfiguration.getValueAsString("test2"));
    }
}