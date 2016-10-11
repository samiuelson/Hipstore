package tech.lab23.hipstore

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.Serializable

class EntityStorage<T : Serializable>(val prefs: SharedPreferences, val clazz: Class<T>) : Hipstore.SingleEntity<T> {

    private val KEY = clazz.simpleName
    private val gson = Gson()
    private val jsonParser = JsonParser()

    override fun remove(item: T) {
        prefs.edit().remove(KEY).apply()
    }

    override fun put(item: T) {
        val jsonElement = gson.toJson(item)
        prefs.edit().putString(KEY, jsonElement.toString()).apply()
    }

    override fun get(): T {
        val json = prefs.getString(KEY, "")
        val element = jsonParser.parse(json)
        return gson.fromJson(element, clazz)
    }

}