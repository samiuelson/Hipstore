package tech.lab23.hipstore

import java.util.*

class TestMocks {

    class Person(val name: String, val age: Int, val annimals: List<Animal>) {

    }

    class Animal(val name: String) {

    }

    object MocksProvider {
        fun provideBob(): Person {
            val animals = LinkedList<Animal>()
            animals.add(Animal("Rex"))
            animals.add(Animal("Viki"))
            return Person("Bob", 23, animals)
        }
        fun provideAlice(): Person {
            val animals = LinkedList<Animal>()
            animals.add(Animal("Sonia"))
            animals.add(Animal("Zeus"))
            return Person("Alice", 27, animals)
        }
    }

}