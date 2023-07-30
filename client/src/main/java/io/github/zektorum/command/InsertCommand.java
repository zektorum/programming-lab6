package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.creation.PersonBuilder;
import io.github.zektorum.data.person.creation.PersonBuilderFromUserInput;

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
    public Person execute(Scanner scanner, CommandArgsArray args) {
        PersonBuilder personBuilder = new PersonBuilderFromUserInput();
        return null;
    }
}
