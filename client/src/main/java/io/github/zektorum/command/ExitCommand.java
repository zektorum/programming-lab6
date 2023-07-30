package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.util.Scanner;

/**
 * Реализация команды exit.
 */
public class ExitCommand extends BaseCommand {
    public ExitCommand() {

    }
    public Person execute(Scanner scanner, CommandArgsArray args) {
        System.out.println("Завершение работы клиента...");
        System.exit(0);
        return null;
    }

    public String getName() {
        return "exit";
    }

    public String getUsage() {
        return "exit";
    }

    public String getDescription() {
        return "завершить программу";
    }

    public int getArgsCount() {
        return 0;
    }
}
