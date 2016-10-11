package tech.lab23.hipstore;

import java.io.Serializable;
import java.util.List;

public interface Hipstore {
    interface MultiEntities<T extends Serializable> {
        boolean contains(T item);
        void remove(T item);
        void add(T item);
        List<T> getAll();
        void clear();
    }

    interface SingleEntity<T extends Serializable> {
        void remove(T item);
        void put(T item);
        T get();
    }
}
