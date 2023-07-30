package io.github.zektorum.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final String propertiesFilename = "application.properties";
    private Properties properties;
    private int port;
    private String storageFilename;

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

    public int getPort() {
         return port;
    }

    public String getStorageFilename() {
        return storageFilename;
    }

    private void initializeProperties() {
        loadProperties();
        initializePort();
        initializeStorageFilename();
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

    private void initializePort() {
        try {
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка! Некорректное значение параметра port в файле application.properties.");
            System.exit(1);
        }
    }

    private void initializeStorageFilename() {
            storageFilename = System.getenv("FILENAME");
        if (storageFilename == null || storageFilename.equals("")) {
            System.err.println("Ошибка! Переменная окружения не установлена.");
            System.exit(1);
        } else if (!new File(storageFilename).exists()) {
            System.err.println("Ошибка! Указанный файл не существует.");
            System.exit(1);
        } else if (!new File(storageFilename).canRead()) {
            System.err.println("Ошибка! Отсутствуют права на чтение.");
            System.exit(1);
        } else if (!new File(storageFilename).canWrite()) {
            System.err.println("Ошибка! Отсутствуют права на запись.");
            System.exit(1);
        }
    }
}
