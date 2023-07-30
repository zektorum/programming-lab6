package io.github.zektorum.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;

import io.github.zektorum.data.person.Person;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Десериализует json.
 */
public class GsonReader implements SerializableFormatReader {
    public Person[] getStructuredData(String content) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                    @Override
                    public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                        out.value(value.toString());
                    }

                    @Override
                    public ZonedDateTime read(JsonReader in) throws IOException {
                        return ZonedDateTime.parse(in.nextString());
                    }
                })
                .create();
        Person[] people = new Person[]{};
        try {
            people = gson.fromJson(content, Person[].class);
        } catch (JsonSyntaxException e) {
            System.out.println("Ошибка парсинга Json! Некорректный синтаксис");
            System.out.println("Завершение работы программы...");
            System.exit(-11);
        }
        return people;
    }
}
