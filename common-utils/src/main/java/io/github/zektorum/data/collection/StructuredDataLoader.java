package io.github.zektorum.data.collection;

import io.github.zektorum.util.ConfigurationManager;
import io.github.zektorum.data.person.Person;
import io.github.zektorum.io.FileReader;
import io.github.zektorum.io.GsonReader;

import java.io.FileNotFoundException;

public class StructuredDataLoader {
    private Person[] data;

    public StructuredDataLoader() {
        String databaseFilename = ConfigurationManager.loadData().getStorageFilename();
        try {
            FileReader fileReader = new FileReader(databaseFilename);
            data = fileReader.read(new GsonReader());
        } catch (FileNotFoundException e) {
            System.err.printf("Ошибка! Файл %s не найден.", databaseFilename);
            System.exit(1);
        }
    }

    public Person[] getData() {
        return data;
    }
}
