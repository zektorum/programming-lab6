package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Реализация команды help.
 */
public class HelpCommand extends BaseCommand implements Serializable {
    private static final long serialVersionUID = 1234123499453L;

    public HelpCommand() {}

    public HelpCommand(Scanner scanner) {
        super(scanner);
    }

    public Person execute(Scanner scanner, CommandArgsArray args) {
        return null;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public int getArgsCount() {
        return 0;
    }
}
