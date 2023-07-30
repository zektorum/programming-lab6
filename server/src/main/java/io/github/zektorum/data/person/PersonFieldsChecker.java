package io.github.zektorum.data.person;

import io.github.zektorum.data.person.Checkable;
import io.github.zektorum.data.person.fields.Location;
import io.github.zektorum.data.person.fields.Color;
import io.github.zektorum.data.person.fields.Coordinates;
import io.github.zektorum.data.person.fields.Country;

/**
 * Содержит валидаторы полей класса Person, а так же самого объекта.
 */
public class PersonFieldsChecker implements Checkable<Person> {
    /**
     * Проверка значения на то, что оно не превосходит <code>limit</code>.
     *
     * @param value проверяемое значение
     * @param limit верхняя граница (ключительно)
     * @return true - если значение не превосходит лимит, в противном случае - else
     */
    public static boolean isMoreThanValue(double value, double limit) {
        double eps = 0.0001;
        return Math.abs(value - limit) > eps && value < limit;
    }

    /**
     * Проверка значения на то, что оно превышает минимум.
     *
     * @param value проверяемое значение
     * @param limit нижняя граница (не включительно)
     * @return true - если значение больше минимума, иначе else
     */
    public static boolean isLessThanValue(Integer value, Integer limit) {
        if (value == null || limit == null)
            return false;
        return value <= limit;
    }

    /**
     * Проверка имени на соответствие критерию.
     *
     * @param name имя человека
     * @return true - если соответствует, иначе false
     */
    public static boolean isValidName(String name) {
        if (name == null)
            return false;
        return !name.equals("");
    }

    /**
     * Проверка локации на валидность.
     *
     * @param location локация человека
     * @return true - если валидна, иначе - false
     */
    public static boolean isValidLocation(Location location) {
        if (location == null)
            return true;
        return location.getY() != null && location.getZ() != null;
    }

    /**
     * Проверка координат на валидность.
     *
     * @param coordinates координаты человека
     * @return true - если валидны, иначе false
     */
    public static boolean isValidCoordinates(Coordinates coordinates) {
        if (coordinates == null)
            return false;
        return PersonFieldsChecker.isLessThanValue(coordinates.getY(), 750);
    }

    /**
     * Проверка констант перечислений на корректность.
     *
     * @param eyeColor цвет глаз человека
     * @param hairColor цвет волос человека
     * @param nationality национальность человека
     * @return true - если все константы корректны, иначе false
     */
    public static boolean isValidEnums(Color.EyeColor eyeColor, Color.HairColor hairColor, Country nationality) {
        try {
            Color.EyeColor.valueOf(eyeColor.toString());
            Color.HairColor.valueOf(hairColor.toString());
            Country.valueOf(nationality.toString());
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Проверка объекта типа Person на соответствие критериям.
     *
     * @param person проверяемый объект
     * @return true - если все критерии соблюдены, иначе false
     */
    public static boolean isValidPerson(Person person) {
        int validFields = 0;

        if (PersonFieldsChecker.isValidName(person.getName()))
            ++validFields;

        if (person.getHeight() > 0)
            ++validFields;

        if (PersonFieldsChecker.isValidLocation(person.getLocation()))
            ++validFields;

        if (PersonFieldsChecker.isValidCoordinates(person.getCoordinates()))
            ++validFields;

        if (PersonFieldsChecker.isValidEnums(person.getEyeColor(), person.getHairColor(), person.getNationality()))
            ++validFields;

        return validFields == 5;
    }
}
