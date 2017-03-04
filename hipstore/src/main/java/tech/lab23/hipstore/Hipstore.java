package tech.lab23.hipstore;

import java.util.List;

interface Hipstore {
    interface MultiEntities<T> {
        boolean contains(T item);
        void remove(T item);
        void add(T item);
        void add(T item, int index);
        List<T> getAll();
        void clear();
    }

    interface SingleEntity<T> {
        void remove(T item);
        void put(T item);
        T get();
    }
}
