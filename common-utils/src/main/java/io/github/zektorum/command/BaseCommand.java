package io.github.zektorum.command;

import java.io.Serializable;

/**
 * Родительский класс для всех команд.
 */
public abstract class BaseCommand implements Command, Serializable {

    public BaseCommand() {}

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

    public abstract int getArgsCount();

    public abstract boolean personInputRequired();
}
