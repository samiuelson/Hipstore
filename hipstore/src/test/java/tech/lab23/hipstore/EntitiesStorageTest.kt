package tech.lab23.hipstore

import android.content.SharedPreferences
import android.preference.PreferenceManager
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class EntitiesStorageTest {
    var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application)


    @Before
    fun init() {
        prefs.edit().clear().apply()
    }

    @Test
    fun contains() {
        // given empty storage
        val storage: EntitiesStorage<TestMocks.Person> =
                EntitiesStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
        org.junit.Assert.assertTrue(storage.all.isEmpty())

        // when Bob is added to storage
        val bob = TestMocks.MocksProvider.provideBob()
        storage.add(bob)

        // then
        org.junit.Assert.assertTrue(storage.contains(bob))
        org.junit.Assert.assertTrue(storage.all.size == 1)
    }

    @Test
    fun remove() {
        // given storage with Bob inside
        val storage: EntitiesStorage<TestMocks.Person> =
                EntitiesStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
        org.junit.Assert.assertTrue(storage.all.isEmpty())
        val bob = TestMocks.MocksProvider.provideBob()
        storage.add(bob)
        Assert.assertTrue(storage.contains(bob))

        // when
        storage.remove(bob)

        // then
        Assert.assertFalse(storage.contains(bob))
        Assert.assertTrue(storage.all.isEmpty())
    }

    @Test
    fun getAll() {
        // given storage
        val storage: EntitiesStorage<TestMocks.Person> =
                EntitiesStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
        org.junit.Assert.assertTrue(storage.all.isEmpty())

        // when
        val bob = TestMocks.MocksProvider.provideBob()
        val ala = TestMocks.MocksProvider.provideAlice()
        storage.add(bob)
        storage.add(ala)

        // then
        Assert.assertFalse(storage.contains(bob))
        Assert.assertFalse(storage.contains(ala))
        Assert.assertTrue(storage.all.contains(bob))
        Assert.assertTrue(storage.all.contains(ala))
        Assert.assertTrue(storage.all.size == 2)
    }

    @Test
    @Throws(Exception::class)
    fun clear() {
        // given storage with Bob and Ala inside
        val storage: EntitiesStorage<TestMocks.Person> =
                EntitiesStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
        org.junit.Assert.assertTrue(storage.all.isEmpty())
        val bob = TestMocks.MocksProvider.provideBob()
        val ala = TestMocks.MocksProvider.provideAlice()
        storage.add(bob)
        storage.add(ala)

        // when
        storage.clear()

        // then
        Assert.assertTrue(storage.all.size == 0)
        Assert.assertTrue(storage.all.isEmpty())

    }

}