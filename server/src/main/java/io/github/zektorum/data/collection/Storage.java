package io.github.zektorum.data.collection;

public interface Storage<T> {
    void put(T element);

    T get(int id);

    void update(int id, T element);

    void remove(int id);

    void remove(T element);
}
