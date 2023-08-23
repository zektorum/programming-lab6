package io.github.zektorum.io;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.github.zektorum.data.person.Person;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Сериализует в json.
 */
public class GsonWriter implements SerializableFormatWriter {
    public String createContentFromStructuredData(Person[] people) {
        return new GsonBuilder()
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
                .create()
                .toJson(people);
    }
}
