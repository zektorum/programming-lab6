package io.github.zektorum.command;

import io.github.zektorum.data.person.Person;

/**
 *  Реализация команды remove_key.
 */
public class RemoveLowerCommand extends BaseCommand {
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
    public String execute(CommandArgsArray args, Person person) {
        return null;
    }
}
