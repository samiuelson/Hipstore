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
class EntityStorageTest {

    var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application)

    @Before
    fun init() {
        prefs.edit().clear().apply()
    }

    @Test
    @Throws(Exception::class)
    fun remove() {
        // given storage with Bob inside
        val storage: EntityStorage<TestMocks.Person> = EntityStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
                EntitiesStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
        val bob = TestMocks.MocksProvider.provideBob()
        storage.put(bob)

        // when
        storage.remove(bob)

        // then
        Assert.assertFalse(storage.get() != null)
    }

    @Test
    @Throws(Exception::class)
    fun put() {
        // given empty storage
        val storage: EntityStorage<TestMocks.Person> = EntityStorage<TestMocks.Person>(prefs, TestMocks.Person::class.java)
        val bob = TestMocks.MocksProvider.provideBob()
        Assert.assertTrue(storage.get() == null)

        // when
        storage.put(bob)

        // then
        Assert.assertTrue(storage.get() != null)
    }

}