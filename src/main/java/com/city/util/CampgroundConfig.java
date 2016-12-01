package com.city.util;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

// There are more sophisticated ways of doing this but they take more time (@link EnableConfiguration).
@Component
public class CampgroundConfig {
    private static final String FILE_NAME = "application.properties";
    private Properties properties;

    public synchronized Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
            try {
                properties.load(inputStream);
            } catch (Exception unused) {
            }
        }
        return properties;
    }

    public double getDoubleValue(String key) {
        return Double.parseDouble(getProperties().getProperty(key));
    }

    public long getLong(String key) {
        return Long.parseLong(getProperties().getProperty(key));
    }

    public String getStringValue(String key) {
        return getProperties().getProperty(key);
    }

    public Boolean getBooleanValue(String key) {
        return Boolean.parseBoolean(getProperties().getProperty(key));
    }
}