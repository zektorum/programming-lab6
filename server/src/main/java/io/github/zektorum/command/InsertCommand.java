package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

import java.util.Scanner;

public class InsertCommand extends BaseCommand {
    private static final long serialVersionUID = 2234123499433L;

    public InsertCommand() {}

    public InsertCommand(Scanner scanner) {}

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getUsage() {
        return "insert key {element}";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент с заданным ключом";
    }

    @Override
    public int getArgsCount() {
        return 3;
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        try {
            Integer.parseInt(args.getArg(0));
        } catch (NumberFormatException e) {
            return "Некорректные аргументы\n";
        }
        PersonStorage storage = PersonStorage.init();
        storage.put(person);
        return null;
    }
}
