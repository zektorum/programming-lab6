package io.github.zektorum.command;

import io.github.zektorum.data.collection.PersonStorage;
import io.github.zektorum.data.person.Person;

public class InfoCommand extends BaseCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getUsage() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
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

    @Override
    public String execute(CommandArgsArray args, Person person) {
        PersonStorage storage = PersonStorage.init();
        return storage.info();
    }
}
