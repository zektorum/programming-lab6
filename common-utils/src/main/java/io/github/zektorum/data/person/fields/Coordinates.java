package io.github.zektorum.data.person.fields;

import java.io.Serializable;

/**
 * Класс, реализующий хранение координат.
 * Все поля класса сериализуются/десериализуются.
 */
public class Coordinates implements Serializable {
    private double x; //Значение поля должно быть больше -183

    private Integer y; //Максимальное значение поля: 750, Поле не может быть null

    /**
     * @param x абсцисса
     * @param y ордината
     */
    public Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }
}
