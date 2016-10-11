package tech.lab23.hipstore;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntitiesStorage<T extends Serializable> implements Hipstore.MultiEntities<T> {

    private final Class<T> clazz;
    private final String key;
    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();
    private final SharedPreferences prefs;

    public EntitiesStorage(SharedPreferences prefs, Class<T> clazz) {
        this.clazz = clazz;
        this.key = clazz.getSimpleName();
        this.prefs = prefs;
    }

    @Override
    public boolean contains(T item) {
        return getAll().contains(item);
    }

    @Override
    public void remove(T item) {
        final List<T> list = getAll();
        list.remove(item);
        prefs.edit().putString(key, listToJsonString(list)).apply();
    }

    @Override
    public void add(T item) {
        final List<T> list = getAll();
        list.add(item);
        prefs.edit().putString(key, listToJsonString(list)).apply();
    }

    @Override
    public List<T> getAll() {
        final String jsonString = prefs.getString(key, "");
        return jsonStringToList(jsonString);
    }

    @Override
    public void clear() {
        prefs.edit().remove(key).apply();
    }

    private String listToJsonString(List<T> list) {
        return gson.toJson(list);
    }

    private List<T> jsonStringToList(String jsonString) {
        final JsonElement jsonElement = jsonParser.parse(jsonString);
        if (jsonElement.isJsonArray()) {
            final JsonArray jsonArray = jsonElement.getAsJsonArray();
            final List<T> list = new ArrayList(jsonArray.size());
            final Iterator<JsonElement> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                final T item = gson.fromJson(iterator.next(), clazz);
                list.add(item);
            }
            return list;
        }
        return new ArrayList<>(0);
    }
}
