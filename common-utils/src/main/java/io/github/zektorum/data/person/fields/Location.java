package io.github.zektorum.data.person.fields;

import java.io.Serializable;

/**
 * Класс, реализующий хранение локации объекта типа Person.
 * Все поля данного класса сериализуются/десериализуются.
 */
public class Location implements Comparable<Location>, Serializable {
    private double x;

    private Float y; //Поле не может быть null

    private Double z; //Поле не может быть null

    /**
     * Создоёт объект на основе переданных координат.
     *
     * @param x абсцисса локации
     * @param y ордината локации
     * @param z аппликата локации
     */
    public Location(double x, float y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int compareTo(Location o) {
        return this.getAbs() - o.getAbs();
    }

    private int getAbs() {
        return (int)Math.sqrt(Math.pow(this.getX(), 2) +
                Math.pow((double)(this.getY()), 2) + Math.pow(this.getZ(), 2));
    }

    /**
     * @return абсцисса локации
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return ордината локации
     */
    public Float getY() {
        return this.y;
    }

    /**
     * @return аппликата локации
     */
    public Double getZ() {
        return this.z;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", this.x, this.y, this.z);
    }
}
