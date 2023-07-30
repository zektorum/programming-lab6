package io.github.zektorum.util;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final String propertiesFilename = "application.properties";
    private Properties properties;
    private String hostname;
    private int port;
    private int timeout;
    private int delta;

    private ConfigurationManager() {}

    public static ConfigurationManager loadData() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.initializeProperties();
        }
        return instance;
    }

    public void updateData() {
        initializeProperties();
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getDelta() {
        return delta;
    }

    private void initializeProperties() {
        loadProperties();
        initializeHostname();
        initializePort();
        initializeTimeout();
        initializeDelta();
    }

    private void loadProperties() {
        try {
            if (properties == null) {
                properties = new Properties();
            }
            properties.load(new FileReader(propertiesFilename));
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка! Убедитесь, что файл application.properties существует.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeHostname() {
        hostname = properties.getProperty("hostname");
    }

    private void initializePort() {
        try {
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка! Некорректное значение параметра port в файле application.properties.");
            System.exit(1);
        }
    }

    private void initializeTimeout() {
        try {
            timeout = Integer.parseInt(properties.getProperty("timeout"));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка! Некорректное значение параметра timeout в файле application.properties.");
            System.exit(1);
        }
    }

    private void initializeDelta() {
        try {
            delta = Integer.parseInt(properties.getProperty("delta"));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка! Некорректное значение параметра delta в файле application.properties.");
            System.exit(1);
        }
    }
}
