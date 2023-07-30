package io.github.zektorum.command;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Родительский класс для всех команд.
 */
public abstract class BaseCommand implements Command, Serializable {
    private final Scanner scanner;

    public BaseCommand() {
        this.scanner = null;
    }

    public BaseCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Возвращает имя команды.
     *
     * @return имя команды
     */
    public abstract String getName();

    /**
     * Возвращает пример использования команды.
     * Строка вида <code>command [arg] {element}</code>, где
     *  [arg] - аргумент команды,
     *  {element} - обозначение ввода элемента с клавиатуры.
     *
     * @return пример использования команды
     */
    public abstract String getUsage();

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    public abstract String getDescription();

    /**
     * Возвращает количество аргументов команды.
     *
     * @return количество аргументов
     */
    public abstract int getArgsCount();
}
