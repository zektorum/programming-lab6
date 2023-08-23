package io.github.zektorum.data.collection;

import java.util.TreeMap;

public interface Storage<T> {
    void put(T element);

    T get(int id);

    void update(int id, T element);

    void remove(int id);

    void remove(T element);

    // TreeMap<Integer, T> getCollection();
}
