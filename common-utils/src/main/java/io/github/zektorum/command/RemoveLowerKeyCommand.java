package io.github.zektorum.command;

import io.github.zektorum.command.validation.RemoveLowerKeyCommandArgsValidator;
import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.collection.StorageAccessOwner;
import io.github.zektorum.data.person.Person;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RemoveLowerKeyCommand extends StorageAccessOwner {

    @Override
    public String getName() {
        return "remove_lower_key";
    }

    @Override
    public String getUsage() {
        return "remove_lower_key null";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, ключ которых меньше, чем заданный";
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
        RemoveLowerKeyCommandArgsValidator validator = new RemoveLowerKeyCommandArgsValidator(args);
        return validator.validateGroup();
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        int id = Integer.parseInt(args.getArg(0));
        TreeMap<Integer, Person> result = storage.stream()
                .filter(x -> x.getId() >= id)
                .collect(Collectors.toMap(Person::getId, Function.identity(),
                        (o1, o2) -> o1, TreeMap::new));
        setCollection(storage, result);
        return "";
    }
}
