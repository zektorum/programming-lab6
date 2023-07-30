package io.github.zektorum.data.person;

public interface Checkable<T> {
    /**
     * Проверка на неравенство null.
     *
     * @param value проверяемое значение
     * @return true - если значение корректное, false - если null
     */
    static boolean isFieldNotNull(Number value) {
        return value != null;
    }
}
