package io.github.zektorum.data.person.creation;

import io.github.zektorum.data.person.Person;

/**
 * Создаёт объект Person по заданной схеме.
 */
public class Director {
    private PersonBuilder personBuilder;

    public Director(PersonBuilder personBuilder) {
        this.personBuilder = personBuilder;
        if (this.personBuilder == null) {
            throw new IllegalArgumentException("Director не может работать с PersonBuilder!");
        }
    }

    /**
     * @return готовый объект Person либо null (если были получены некорректные значения).
     */
    public Person createPerson() {
        return this.personBuilder
                .withName()
                .withHeight()
                .withLocation()
                .withCoordinates()
                .withEyeColor()
                .withHairColor()
                .withNationality()
                .build();
    }
}
