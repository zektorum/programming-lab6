package io.github.zektorum.io;

import io.github.zektorum.data.person.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Класс, преобразующий объекты из текстового формата в структурированный.
 */
public class FileWriter implements Writable {
    private String filename;

    public FileWriter(String filename) {
        this.filename = filename;
    }

    public void write(SerializableFormatWriter writer, Person[] people) throws IOException {
        OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(this.filename));
        for (char symbol : writer.createContentFromStructuredData(people).toCharArray()) {
            outputStream.write(symbol);
        }
        outputStream.flush();
        outputStream.close();
    }
}
