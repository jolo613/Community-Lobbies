package me.x1xx.lib.database;

/**
 * A class that contains the settings for a generic database.
 */
public class DatabaseSettings {
    public DatabaseType type;
    public boolean fallbackSqlite;
    public String host;
    public String port;
    public String database;
    public String username;
    public String password;

    public DatabaseSettings(DatabaseType type, boolean fallbackSqlite, String host, String port, String database, String username, String password) {
        this.type = type;
        this.fallbackSqlite = fallbackSqlite;
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
}
