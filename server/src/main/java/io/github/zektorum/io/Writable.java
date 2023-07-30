package io.github.zektorum.io;

import io.github.zektorum.data.person.Person;

import java.io.IOException;

/**
 * Используется для сериализации данных.
 */
public interface Writable {
    /**
     * @param writer класс-оболочка для работы со структурированным форматом
     * @param people массив объектов
     * @throws IOException
     */
    void write(SerializableFormatWriter writer, Person[] people) throws IOException;
}
