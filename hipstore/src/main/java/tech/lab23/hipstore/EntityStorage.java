package tech.lab23.hipstore;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class EntityStorage<T> implements Hipstore.SingleEntity<T> {

    private final Class<T> clazz;
    private final String key;
    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();
    private final SharedPreferences prefs;

    public EntityStorage(SharedPreferences prefs, Class<T> clazz) {
        this.clazz = clazz;
        this.key = clazz.getSimpleName();
        this.prefs = prefs;
    }

    public EntityStorage(SharedPreferences prefs, Class<T> clazz, String storageKey) {
        this.clazz = clazz;
        this.key = storageKey;
        this.prefs = prefs;
    }

    @Override
    public void remove(T item) {
        prefs.edit().remove(key).apply();
    }

    @Override
    public void put(T item) {
        final String jsonElement = gson.toJson(item);
        prefs.edit().putString(key, jsonElement).apply();
    }

    @Override
    public T get() {
        final String json = prefs.getString(key, "");
        final JsonElement element = jsonParser.parse(json);
        return gson.fromJson(element, clazz);
    }
}
