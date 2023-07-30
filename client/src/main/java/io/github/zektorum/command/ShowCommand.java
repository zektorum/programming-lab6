package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.util.Scanner;

/**
 * Реализация команды show.
 */

public class ShowCommand extends BaseCommand{
    private static final long serialVersionUID = 2234123499453L;

    public ShowCommand() {}

    public ShowCommand(Scanner scanner) {}

    @Override
    public Person execute(Scanner scanner, CommandArgsArray args) {
        return null;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getUsage() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public int getArgsCount() {
        return 0;
    }
}
