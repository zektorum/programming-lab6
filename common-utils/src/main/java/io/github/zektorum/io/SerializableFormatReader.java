package io.github.zektorum.io;

import io.github.zektorum.data.person.Person;

/**
 * Интерфейс, определяющий формат работы со структурированными данными в текстовом виде.
 */
public interface SerializableFormatReader {
    /**
     * @param content структурированные данные в текстовом представлении
     * @return объекты типа Person
     */
    Person[] getStructuredData(String content);
}
