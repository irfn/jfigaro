package com.gojek;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({YamlConfiguration.class})
public class YamlConfigurationTest {
    @Test
    public void shouldLoadConfig() throws FileNotFoundException {
        YamlConfiguration test = new YamlConfiguration("development", "/application-yaml-configuration-test.yml");
        assertEquals("foo", test.getValue("shouldLoadConfig"));
    }

    @Test
    public void shouldLoadConfigBasedOnEnvironment() throws FileNotFoundException {
        YamlConfiguration test = new YamlConfiguration("test", "/application-yaml-configuration-test.yml");
        assertEquals("bar", test.getValue("shouldLoadConfigBasedOnEnvironment"));
    }

    @Test
    public void shouldUseDefaultConfiWhenSpecificValueIsNotSet() throws FileNotFoundException {
        YamlConfiguration test = new YamlConfiguration("test", "/application-yaml-configuration-test.yml");
        assertEquals("defaultFoo", test.getValue("shouldUseDefaultConfiWhenSpecificValueIsNotSet"));
    }

    @Test
    public void shouldUseDefaultConfigWhenSpecificEncironmentIsNotSet() throws FileNotFoundException {
        YamlConfiguration test = new YamlConfiguration("something", "/application-yaml-configuration-test.yml");
        assertEquals("defaultFoo", test.getValue("shouldUseDefaultConfiWhenSpecificValueIsNotSet"));
    }

    @Test
    public void shouldAllowEnvironmentOverride() throws FileNotFoundException {
        mockStatic(System.class);
        when(System.getenv("shouldUseDefaultConfiWhenSpecificValueIsNotSet")).thenReturn("envOverrideFoo");
        YamlConfiguration test = new YamlConfiguration("something", "/application-yaml-configuration-test.yml");
        assertEquals("envOverrideFoo", test.getValue("shouldUseDefaultConfiWhenSpecificValueIsNotSet"));
    }
}