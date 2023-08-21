package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

import java.util.Scanner;

/**
 *  Реализация команды remove_key.
 */
public class RemoveKeyCommand extends BaseCommand {
    private static final long serialVersionUID = 9234123499413L;

    public RemoveKeyCommand() {}

    public RemoveKeyCommand(Scanner scanner) {}

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String getUsage() {
        return "remove_key key";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его ключу";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public Person execute(Scanner scanner, CommandArgsArray args) {
        return null;
    }
}
