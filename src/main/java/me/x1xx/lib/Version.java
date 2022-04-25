package me.x1xx.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Version command to get the current version of the bot.
 */
public class Version {
    public static final String APPLICATION_VERSION;

    static {
        String temp;
        try (InputStream input = Version.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            temp = (String) properties.getOrDefault("version", "UNKNOWN VERSION");
        } catch (IOException e) {
            temp = "UNKNOWN VERSION";
        }
        APPLICATION_VERSION = temp;
    }



}
