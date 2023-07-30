package io.github.zektorum.data.person.fields;

import java.io.Serializable;

/**
 * Класс-оболочка для enum EyeColor и HairColor.
 */
public class Color implements Serializable {
    /**
     * Перечисление, элементами которого являются константы цвета волос
     */
    public enum HairColor {
        GREEN(0), BLUE(1), WHITE(2);

        private int value;

        HairColor(int value){
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * Перечисление, элементами которого являются константы цвета глаз
     */
    public enum EyeColor {
        RED(0), BLACK(1), BLUE(2),
        ORANGE(3), BROWN(4);

        private int value;

        EyeColor(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
