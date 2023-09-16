package io.github.zektorum.data.collection;

import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.PersonFieldsChecker;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.TreeMap;

public final class PersonStorage implements Storage<Person> {
    private static PersonStorage instance;
    private StructuredDataLoader dataLoader;
    private TreeMap<Integer, Person> collection;
    private CRUDManager databaseManager;
    private ZonedDateTime initializationDate;
    private int skippedElements;
    private List<Integer> usedIds;

    private PersonStorage() {}

    public static PersonStorage init() { // TODO: rename to load()
        if (instance == null) {
            instance = new PersonStorage();
            instance.usedIds = new LinkedList<>();
            instance.dataLoader = new StructuredDataLoader();
            Person[] structuredData = instance.dataLoader.getData();
            instance.initCollection(structuredData);
        }
        return instance;
    }

    private void initCollection(Person[] structuredData) {
        if (structuredData.length == 0) {
            System.out.println("Введён пустой файл. Завершение работы...");
            System.exit(1);
        }
        skippedElements = 0;
        collection = new TreeMap<>();
        for (Person person : structuredData) {
            if (!PersonFieldsChecker.isValidPerson(person)) {
                System.out.printf(
                        "[WARNING] Объект c именем %s не соответствует критериям и не добавлен в коллекцию\n",
                        person.getName());
                ++skippedElements;
                continue;
            }
            person.setId(generateId());
            collection.put(person.getId(), person);
            usedIds.add(person.getId());
        }
        initializationDate = ZonedDateTime.now();
    }

    private int generateId() {
        for (int id = 1; ; ++id) {
            if (usedIds.contains(id)) {
                continue;
            }
            usedIds.add(id);
            return id;
        }
    }

    private boolean isValidId(int id) {
        if (collection.get(id) == null || id < 1) {
            System.out.printf("Элемент с id %s отсутствует!\n", id);
            return false;
        }
        return true;
    }

    TreeMap<Integer, Person> getCollection() {
        return collection;
    }

    void setCollection(TreeMap<Integer, Person> collection) {
        this.collection = collection;
    }

    public String getElementInfo(Integer key, Person person) {
        String format;
        if (person.getLocation() != null) {
            format = "Имя: %s\nId:  %s\nРост: %s\nДата создания: %s\nЛокация: (%.1f, %.1f, %.1f)\nКоординаты: " +
                    "(%.1f, %d)\nЦвет глаз: %s\nЦвет волос: %s\nНациональность: %s\n\n";
        } else {
            format = "Имя: %s\nId:  %s\nРост: %s\nДата создания: %s\nЛокация: (%s, %s, %s)\nКоординаты: (%s, %s)\n" +
                    "Цвет глаз: %s\nЦвет волос: %s\nНациональность: %s\n\n";
        }
        return String.format(
                format,
                person.getName(),
                person.getId(),
                person.getHeight(),
                person.getCreationDate().toString(),
                person.getLocation() != null ? person.getLocation().getX() : null,
                person.getLocation() != null ? person.getLocation().getY() : null,
                person.getLocation() != null ? person.getLocation().getZ() : null,
                person.getCoordinates() != null ? person.getCoordinates().getX() : null,
                person.getCoordinates() != null ? person.getCoordinates().getY() : null,
                person.getEyeColor(),
                person.getHairColor(),
                person.getNationality());
    }

    public Stream<Person> stream() {
        return collection.values().stream();
    }
    @Override
    public void put(Person element) {
        int id = generateId();
        element.setId(id);
        collection.put(id, element);
        usedIds.add(id);
        save();
    }

    @Override
    public Person get(int id) {
        return collection.get(id);
    }

    @Override
    public void update(int id, Person element) {
        if (!isValidId(id))
            return;
        element.setId(id);
        collection.put(id, element);
        save();
    }

    @Override
    public void remove(int id) {
        if (!isValidId(id))
            return;
        collection.remove(id);
        save();
    }

    @Override
    public void remove(Person element) {
        if (collection.get(element.getId()) == null)
            return;
        collection.values().remove(element);
        save();
    }

    public void info() {
        System.out.printf("Тип: %s\nДата инициализации: %s\nКоличество элементов: %s\nКоличество " +
                        "некорректных (прорущенных) элементов: %d\n",
                collection.getClass().getName(), initializationDate, collection.size(), skippedElements);
    }

    private void save() {
        StructuredDataSaver dataSaver = new StructuredDataSaver(collection);
        dataSaver.save();
    }
}
