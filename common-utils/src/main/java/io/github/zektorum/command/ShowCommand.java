package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.collection.StorageAccessOwner;
import io.github.zektorum.data.person.Person;

/**
 * Реализация команды show.
 */
public class ShowCommand extends StorageAccessOwner {
    private static final long serialVersionUID = 2234123499453L;

    public ShowCommand() {}

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        return storage.stream()
                .map(element -> storage.getElementInfo(element.getId(), element)).reduce("", String::concat);
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getUsage() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public int getArgsCount() {
        return 0;
    }

    @Override
    public boolean personInputRequired() {
        return false;
    }

    @Override
    public boolean validate(CommandArgsArray args) {
        return true;
    }
}
