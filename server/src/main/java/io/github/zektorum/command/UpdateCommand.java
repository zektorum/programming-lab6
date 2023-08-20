package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

import java.util.Scanner;

public class UpdateCommand extends BaseCommand {
    private static final long serialVersionUID = 9234123499433L;

    public UpdateCommand() {}

    public UpdateCommand(Scanner scanner) {}

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getUsage() {
        return "update id {element}";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        String id = args.getArg(0);
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "Некорректные аргументы!\n";
        }
        PersonStorage storage = PersonStorage.init();
        storage.update(Integer.parseInt(id), person);
        return "";
    }
}
