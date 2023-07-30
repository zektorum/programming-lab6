package io.github.zektorum.data.collection;

import io.github.zektorum.command.BaseCommand;
import io.github.zektorum.data.person.Person;

import java.util.Scanner;
import java.util.TreeMap;

public abstract class StorageAccessOwner extends BaseCommand {
    public StorageAccessOwner() {}

    public StorageAccessOwner(Scanner scanner) {
        super(scanner);
    }

    public TreeMap<Integer, Person> collection(PersonStorage storage) {
        return storage.getCollection();
    }
}
