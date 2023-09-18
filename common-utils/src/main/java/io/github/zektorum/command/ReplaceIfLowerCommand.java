package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.collection.StorageAccessOwner;
import io.github.zektorum.data.person.Person;


public class ReplaceIfLowerCommand extends StorageAccessOwner {
    @Override
    public String getName() {
        return "replace_if_lower";
    }

    @Override
    public String getUsage() {
        return "replace_if_lower {element}";
    }

    @Override
    public String getDescription() {
        return "заменить значение по ключу, если новое значение меньше старого";
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
        return true;
    }

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();

        int id = Integer.parseInt(args.getArg(0));
        person.setId(id);

        if (person.compareTo(storage.get(id)) > 0) {
            storage.update(id, person);
        }
        return "";
    }
}
