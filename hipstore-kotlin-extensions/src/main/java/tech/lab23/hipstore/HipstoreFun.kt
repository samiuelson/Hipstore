package tech.lab23.hipstore

import android.content.SharedPreferences

inline fun <reified T: Any> entityStorage(pref: SharedPreferences) = EntityStorage<T>(pref, T::class.java)

inline fun <reified T: Any> entitiesStorage(pref: SharedPreferences) = EntitiesStorage<T>(pref, T::class.java)