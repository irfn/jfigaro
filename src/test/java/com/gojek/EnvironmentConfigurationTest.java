package com.gojek;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EnvironmentConfiguration.class})
public class EnvironmentConfigurationTest {
    @Test
    public void shouldGetEnvironmentValue() {
        mockStatic(System.class);
        String expectedValue = "envValue";
        when(System.getenv("shouldGetEnvironmentValue")).thenReturn(expectedValue);
        EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration();
        assertEquals(expectedValue, environmentConfiguration.getValue("shouldGetEnvironmentValue"));

    }
}