package io.github.zektorum.data.collection;

import io.github.zektorum.data.person.Person;
import io.github.zektorum.io.FileWriter;
import io.github.zektorum.io.GsonWriter;
import io.github.zektorum.util.ConfigurationManager;

import java.io.IOException;
import java.util.TreeMap;

public class StructuredDataSaver {
    private TreeMap<Integer, Person> data;

    public StructuredDataSaver(TreeMap<Integer, Person> data) {
        this.data = data;
    }

    public void save() {
        String databaseFilename = ConfigurationManager.loadData().getStorageFilename();
        try {
            FileWriter fileWriter = new FileWriter(databaseFilename);
            Person[] people = new Person[]{};
            fileWriter.write(new GsonWriter(), data.values().toArray(people));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
