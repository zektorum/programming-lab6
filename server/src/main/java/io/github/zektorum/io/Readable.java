package io.github.zektorum.io;

import io.github.zektorum.data.person.Person;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Используется для чтения структурированных типов данных.
 */
public interface Readable {
    /**
     * @param reader класс-оболочка для структутированного формата
     * @return строка, содержащая данные в текстовом формате
     * @throws FileNotFoundException выбрасывается если файл не существует
     */
    Person[] read(SerializableFormatReader reader) throws FileNotFoundException;
}
