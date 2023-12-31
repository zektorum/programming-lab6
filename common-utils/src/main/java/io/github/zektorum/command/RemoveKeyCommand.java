package io.github.zektorum.command;

import io.github.zektorum.command.validation.RemoveKeyCommandArgsValidator;
import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

/**
 *  Реализация команды remove_key.
 */
public class RemoveKeyCommand extends BaseCommand {
    private static final long serialVersionUID = 9234123499413L;

    public RemoveKeyCommand() {}

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
    public boolean personInputRequired() {
        return false;
    }

    @Override
    public boolean validate(CommandArgsArray args) {
        RemoveKeyCommandArgsValidator validator = new RemoveKeyCommandArgsValidator(args);
        return validator.validateGroup();
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
        storage.remove(Integer.parseInt(id));
        return "";
    }
}
