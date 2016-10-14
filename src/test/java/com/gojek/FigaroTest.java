package com.gojek;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Figaro.class } )
public class FigaroTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnFileBasedConfigurationsForTestEnvironment() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("test");
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnFileBasedConfigurationsForDevEnvironment() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("development");
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnFileBasedConfigurationsForNullEnvironment() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnEnvironmentBasedConfigurationsForAnyOtherEnvironment() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("prod");
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(EnvironmentConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldDefaultEnvironmentToDevelopment() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldConfigureYamlFilename() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn("development");
        when(System.getProperty("figaro.yaml.filename")).thenReturn("/override-application-yaml-configuration-test.yml");
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(YamlConfiguration.class, configurations.getClass());
        assertTrue(configurations.getValueAsBoolean("overridden"));
    }

    @Test
    public void shouldReturnFileBasedConfigurationsForNullRequiredKeys() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        ApplicationConfiguration configurations = Figaro.configure(null);
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldReturnFileBasedConfigurationsForEmptyRequiredKeySet() throws MissingKeysException {
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        ApplicationConfiguration configurations = Figaro.configure(Collections.emptySet());
        assertEquals(YamlConfiguration.class, configurations.getClass());
    }

    @Test
    public void shouldThrowMissingKeysExceptionForNotFoundRequiredKey() throws MissingKeysException {
        thrown.expect(MissingKeysException.class);
        thrown.expectMessage("Missing required configuration keys: [NOT_SET_KEY_1, NOT_SET_KEY_2]");
        mockStatic(System.class);
        when(System.getenv("APP_ENVIRONMENT")).thenReturn(null);
        Set<String> requiredKeys = new HashSet<String> (){{
            add("NOT_SET_KEY_1");
            add("NOT_SET_KEY_2");
        }};
        ApplicationConfiguration configurations = Figaro.configure(requiredKeys);
    }
}
