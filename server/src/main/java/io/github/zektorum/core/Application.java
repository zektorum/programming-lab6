package io.github.zektorum.core;

import io.github.zektorum.util.ConfigurationManager;

import java.io.IOException;

/**
 * Точка входа в приложение.
 */
public class Application {
    private ConfigurationManager configurationManager;
    private final String[] args;

    /**
     * Создаёт объект приложения. Конструктор инициализирует поле класса аргументами командной строки,
     * с которыми было запущено приложение.
     *
     * @param args массив аргументов командной строки
     */
    public Application(String[] args) {
        this.configurationManager = ConfigurationManager.loadData();
        this.args = args;
    }

    /**
     * Метод, запускающий приложение.
     */
    public void run() {
        try {
            new Server(configurationManager.getPort()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
