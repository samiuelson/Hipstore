package tech.lab23.hipstore

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import java.io.Serializable
import java.util.*

class EntitiesStorage<T : Serializable>(val prefs: SharedPreferences, val clazz: Class<T>) : Hipstore.MultiEntities<T> {
    private val KEY = clazz.simpleName
    private val gson = Gson()
    private val jsonParser = JsonParser()

    override fun contains(item: T): Boolean {
        return getAll().contains(item)
    }

    override fun remove(item: T) {
        val list = getAll()
        list.remove(item)
        prefs.edit().putString(KEY, listToJsonString(list)).apply()
    }

    override fun add(item: T) {
        val list = getAll()
        list.add(item)
        prefs.edit().putString(KEY, listToJsonString(list)).apply()
    }

    override fun getAll(): MutableList<T> {
        val jsonString = prefs.getString(KEY, "")
        return jsonStringToList(jsonString)
    }

    override fun clear() {
        prefs.edit().remove(KEY).apply()
    }

    private fun listToJsonString(list: List<T>) : String {
        return gson.toJson(list)
    }

    private fun jsonStringToList(jsonString: String): MutableList<T> {
        val jsonElement = jsonParser.parse(jsonString)
        if (jsonElement.isJsonArray) {
            val jsonArray: JsonArray = jsonElement.asJsonArray
            val list: MutableList<T> = ArrayList(jsonArray.size())
            val iterator = jsonArray.iterator()
            while (iterator.hasNext()) {
                val item: T = gson.fromJson(iterator.next(), clazz)
                list.add(item)
            }
            return list
        }
        return ArrayList<T>(0)
    }
}

