package io.github.zektorum.command;

import io.github.zektorum.command.validation.InsertCommandArgsValidator;
import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

public class InsertCommand extends BaseCommand {
    private static final long serialVersionUID = 2234123499433L;

    public InsertCommand() {}

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
        return 1;
    }

    @Override
    public boolean personInputRequired() {
        return true;
    }

    @Override
    public boolean validate(CommandArgsArray args) {
        InsertCommandArgsValidator validator = new InsertCommandArgsValidator(args);
        return validator.validateGroup();
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        String id = args.getArg(0);
        storage.put(Integer.parseInt(id), person);
        return "";
    }
}
