package me.x1xx.lib.database.imp;

import me.x1xx.lib.database.Database;
import me.x1xx.lib.database.DatabaseSettings;
import me.x1xx.lib.database.exception.NoConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * An implementation of the {@link Database} interface for MySQL.
 */
public class MySQLDatabase implements Database<DatabaseSettings> {
    private final DatabaseSettings settings;
    private final String url;

    public MySQLDatabase(DatabaseSettings settings) {
        this.settings = settings;
        this.url = "jdbc:mysql://" + settings.host + "/" + settings.database + ":" + settings.port + "?user=" + settings.username + "&password=" + settings.password;
    }

    @Override
    public Connection connect(DatabaseSettings settings) throws NoConnectionException {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new NoConnectionException(e.getClass().getName() + ": " + e.getMessage());
        }
        return c;
    }



}
