
package me.x1xx.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A manager class to load configurations from files.
 */
@SuppressWarnings("all")
public class ConfigManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public <T> Config<T> loadConfig(String name, T defaults, Class<?> clazz) {
        return new Config<T>(name, gson, defaults, clazz);
    }

    public Gson getGson() {
        return gson;
    }
}


