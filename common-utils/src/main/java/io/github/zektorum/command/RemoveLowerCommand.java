package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.collection.StorageAccessOwner;
import io.github.zektorum.data.person.Person;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  Реализация команды remove_key.
 */
public class RemoveLowerCommand extends StorageAccessOwner {
    private static final long serialVersionUID = 9134123499413L;

    public RemoveLowerCommand() {}

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getUsage() {
        return "remove_lower {element}";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public int getArgsCount() {
        return 0;
    }

    @Override
    public boolean personInputRequired() {
        return true;
    }

    @Override
    public boolean validate(CommandArgsArray args) {
        return true;
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        TreeMap<Integer, Person> result = storage.stream()
                .filter(x -> x.getName().length() >= person.getName().length())
                .collect(Collectors.toMap(Person::getId, Function.identity(),
                        (o1, o2) -> o1, TreeMap::new));
        setCollection(storage, result);
        return "";
    }
}
