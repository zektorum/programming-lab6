package io.github.zektorum.data.person.creation;

import io.github.zektorum.data.person.Person;
import io.github.zektorum.data.person.PersonFieldsChecker;
import io.github.zektorum.data.person.fields.*;
import io.github.zektorum.io.InputChecker;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Схема создания объекта Person на основе данных из файла.
 */
public class PersonBuilderFromFile implements PersonBuilder, InputChecker {
    final private Scanner input;
    private String name;
    private Coordinates coordinates;
    private int height;
    private Color.EyeColor eyeColor;
    private Color.HairColor hairColor;
    private Country nationality;
    private Location location;

    public PersonBuilderFromFile(Scanner input) {
        this.input = input;
    }

    public PersonBuilder withName() {
        checkInput(this.input);
        String name = this.input.nextLine();
        if (name.equals("")) {
            this.name =  null;
        } else {
            this.name = name;
        }
        return this;
    }

    public PersonBuilder withCoordinates() {
        checkInput(this.input);
        CoordinatesReader coordinatesReader = new CoordinatesReader();
        this.coordinates = coordinatesReader.readFromFile(this.input);
        return this;
    }

    public PersonBuilder withHeight() {
        try {
            checkInput(this.input);
            this.height = this.input.nextInt();
        } catch (InputMismatchException e) {
            this.height = -1;
        }
        return this;
    }
    public PersonBuilder withEyeColor() {
        checkInput(this.input);
        String eyeColor = this.input.next().toUpperCase();
        try {
            this.eyeColor = Color.EyeColor.valueOf(eyeColor);
        } catch (IllegalArgumentException e) {
            this.eyeColor = null;
        }
        return this;
    }

    public PersonBuilder withHairColor() {
        checkInput(this.input);
        String hairColor = this.input.next().toUpperCase();
        try {
            this.hairColor = Color.HairColor.valueOf(hairColor);
        } catch (IllegalArgumentException e) {
            this.hairColor = null;
        }
        return this;
    }

    public PersonBuilder withNationality() {
        checkInput(this.input);
        String nationality = this.input.next().toUpperCase();
        try {
            this.nationality = Country.valueOf(nationality);
        } catch (IllegalArgumentException e) {
            this.nationality = null;
        }
        return this;
    }

    public PersonBuilder withLocation() {
        checkInput(this.input);
        LocationReader locationReader = new LocationReader();
        this.location = locationReader.readFromFile(this.input);
        return this;
    }

    public Person build() {
        Person person = new Person(name, height, location, coordinates, eyeColor, hairColor, nationality);
        if (PersonFieldsChecker.isValidPerson(person)) {
            return person;
        } else {
            return null;
        }
    }
}
