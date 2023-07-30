package io.github.zektorum.io;

import io.github.zektorum.data.person.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс, преобразующий данные из файла в текстовый формат.
 */
public class FileReader implements Readable {
    private String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }

    public Person[] read(SerializableFormatReader reader) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new FileInputStream(this.filename));
        String content = "";
        while (fileScanner.hasNextLine()) {
            content += fileScanner.nextLine();
        }
        fileScanner.close();
        return reader.getStructuredData(content);
    }
}
