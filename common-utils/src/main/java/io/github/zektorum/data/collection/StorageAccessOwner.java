package io.github.zektorum.data.collection;

import io.github.zektorum.command.BaseCommand;
import io.github.zektorum.data.person.Person;

import java.util.TreeMap;

public abstract class StorageAccessOwner extends BaseCommand {
    public StorageAccessOwner() {}

    protected TreeMap<Integer, Person> collection(PersonStorage storage) {
        return storage.getCollection();
    }

    protected void setCollection(PersonStorage storage, TreeMap<Integer, Person> collection) {
        storage.setCollection(collection);
    }
}
