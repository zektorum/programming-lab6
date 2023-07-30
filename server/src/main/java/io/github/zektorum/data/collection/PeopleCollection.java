package io.github.zektorum.data.collection;

import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.PersonFieldsChecker;
import io.github.zektorum.io.FileWriter;
import io.github.zektorum.io.GsonWriter;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

/**
 * Класс-обёртка для хранения коллекции типа TreeMap.
 */
public class PeopleCollection implements Serializable {
    private final TreeMap<Integer, Person> people;
    private final ZonedDateTime initializationDate;
    private final ArrayList<Integer> usedIds;
    private final String filename;
    private int skippedElements;

    /**
     * Инициализирует коллекцию десериализованными данными.
     *
     * @param structuredData объекты, полученные из файла
     */
    public PeopleCollection(Person[] structuredData) {
        this.people = new TreeMap<>();
        this.usedIds = new ArrayList<>();
        this.filename = System.getenv("FILENAME");
        this.skippedElements = 0;
        if (structuredData == null) {
            System.out.println("Введён пустой файл. Завершение работы...");
            System.exit(-3);
        }
        for (Person person : structuredData) {
            if (!PersonFieldsChecker.isValidPerson(person)) {
                System.out.printf(
                        "[WARNING] Объект c именем %s не соответствует критериям и не добавлен в коллекцию\n",
                        person.getName()
                );
                ++this.skippedElements;
                continue;
            }
            person.setId(this.generateId());
            this.people.put(person.getName().hashCode(), person);
            this.usedIds.add(person.getId());
        }
        this.initializationDate = ZonedDateTime.now();
    }

    /**
     * @return коллекция объектов Person
     */
    public TreeMap<Integer, Person> getPeopleCollection() {
        return this.people;
    }

    /**
     * @param key ключ коллекции
     */
    public void removeElementByKey(int key) {
        if (this.isValidId(key)){
            this.people.remove(key);
        }
    }

    /**
     * Реализация команды clear.
     */
    public void clearCollection() {
        this.usedIds.clear();
        this.people.clear();
    }

    /**
     * Реализация команды save.
     *
     * @throws IOException если файл с коллекцией недоступен для записи
     */
    public void saveAsFile() throws IOException {
        FileWriter writer = new FileWriter(this.filename);
        Person[] peopleArray = new Person[this.people.size()];
        int i = 0;
        for (Map.Entry<Integer, Person> person : this.people.entrySet()) {
            peopleArray[i] = person.getValue();
            ++i;
        }
        writer.write(new GsonWriter(), peopleArray);
    }

    /**
     * Выводит один элемент коллекции в консоль.
     *
     * @param key ключ элемента
     * @param person элемент
     */
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

    /**
     * Выводит все элементы коллекции в консоль.
     */
    public void showAll() {
        if (!this.people.isEmpty()) {
            for (Person element : this.people.values()) {
                System.out.println(this.getElementInfo(element.getId(), element));
            }
        } else {
            System.out.println();
        }
    }

    public String getAll() {
        Supplier<String> elementsInfo = () -> {
            StringBuilder elements = new StringBuilder();
            for (Person element : this.people.values()) {
                elements.append(this.getElementInfo(element.getId(), element));
            }
            return elements.toString();
        };
        return elementsInfo.get();
    }

    /**
     * Выводит в консоль информацию о коллекции.
     */
    public void info() {
        System.out.printf(
                "Тип: %s\nДата инициализации: %s\nКоличество элементов: %s\n" +
                        "Количество некорректных (прорущенных) элементов: %d\n",
                this.people.getClass().getName(),
                this.initializationDate,
                this.people.size(),
                this.skippedElements
        );
    }

    public void insert(Integer key, Person person) {
        this.people.put(key, person);
    }

    /**
     * Реализует команду average_of_height.
     *
     * @return средний рост
     */
    public double averageOfHeight() {
        int peopleCount = this.people.size();
        if (peopleCount == 0) {
            return 0d;
        }
        return this.people.values().stream().mapToDouble(Person::getHeight).sum() / peopleCount;
    }

    /**
     * Генерирует уникальный id.
     *
     * @return id
     */
    private int generateId() {
        for (int id = 1; ; ++id) {
            if (this.usedIds.contains(id)) {
                continue;
            }
            this.usedIds.add(id);
            return id;
        }
    }

    /**
     * Проверка на валидность идентификатора пользователя.
     *
     * @param id идентификатор пользователя
     * @return для валидных id - true, для остальных - false
     */
    private boolean isValidId(int id) {
        if (this.people.get(id) == null || id < 1) {
            System.out.printf("Элемент с id %s отсутствует!\n", id);
            return false;
        }
        return true;
    }
}
