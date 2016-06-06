package com.gojek;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Figaro.class } )
public class FigaroTest {
    @Test
    public void shouldReturnFileBasedConfigurationsForTestEnvironment() {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("test");
        ApplicationConfiguration configurations = Figaro.configure();
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnFileBasedConfigurationsForDevEnvironment() {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("development");
        ApplicationConfiguration configurations = Figaro.configure();
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnFileBasedConfigurationsForNullEnvironment() {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        ApplicationConfiguration configurations = Figaro.configure();
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnEnvironmentBasedConfigurationsForAnyOtherEnvironment() {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("prod");
        ApplicationConfiguration configurations = Figaro.configure();
        assertEquals(EnvironmentConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldDefaultEnvironmentToDevelopment() {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        ApplicationConfiguration configurations = Figaro.configure();
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }
}
