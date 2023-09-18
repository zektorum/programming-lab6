package io.github.zektorum.command;

import io.github.zektorum.command.validation.UpdateCommandArgsValidator;
import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

public class UpdateCommand extends BaseCommand {
    private static final long serialVersionUID = 9234123499433L;

    public UpdateCommand() {}

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
    public boolean personInputRequired() {
        return true;
    }

    @Override
    public boolean validate(CommandArgsArray args) {
        UpdateCommandArgsValidator validator = new UpdateCommandArgsValidator(args);
        return validator.validateGroup();
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        String id = args.getArg(0);
        storage.update(Integer.parseInt(id), person);
        return "";
    }
}
