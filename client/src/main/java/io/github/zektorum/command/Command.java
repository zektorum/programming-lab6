package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.util.Scanner;

/**
 * Функциональный интерфейс Command.
 */
public interface Command {
    /**
     * Метод, запускающий команду.
     */
    Person execute(Scanner scanner, CommandArgsArray args);
}
