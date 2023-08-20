package io.github.zektorum.command;

// import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.core.Interpreter;
import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.creation.Director;
import io.github.zektorum.data.person.creation.PersonBuilder;
import io.github.zektorum.data.person.creation.PersonBuilderFromFile;
import io.github.zektorum.data.person.creation.PersonBuilderFromUserInput;

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
    public Person execute(Scanner scanner, CommandArgsArray args) {
        PersonBuilder personBuilder;
        if (!Interpreter.scriptsStack.get(Interpreter.scriptsStack.size() - 1).equals("Main")) { // TODO: add method call instead of boolean expression
            personBuilder = new PersonBuilderFromFile(Interpreter.scanner);
        } else {
            personBuilder = new PersonBuilderFromUserInput();
        }
        Director director = new Director(personBuilder);
        return director.createPerson();
    }
}
