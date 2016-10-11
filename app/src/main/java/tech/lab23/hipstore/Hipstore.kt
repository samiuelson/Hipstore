package tech.lab23.hipstore

import java.io.Serializable

interface Hipstore {

    interface MultiEntities<T : Serializable> {
        fun contains(item: T): Boolean
        fun remove(item: T)
        fun add(item: T)
        fun getAll(): MutableList<T>
        fun clear()
    }

    interface SingleEntity<T : Serializable> {
        fun remove(item: T)
        fun put(item: T)
        fun get(): T
    }

}