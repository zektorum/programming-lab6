package io.github.zektorum.io;

import io.github.zektorum.data.person.Person;

/**
 * Интерфейс, определяющий формат работы с объетами, которые планируется сериализировать.
 */
public interface SerializableFormatWriter {
    /**
     * @param people объекты типа Person
     * @return структурированные данные в текстовом представлении
     */
    String createContentFromStructuredData(Person[] people);
}
