package me.x1xx.lib.database;

import me.x1xx.lib.database.exception.NoConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * An interface for a database.
 * @param <T> The type of settings to use.
 */
public interface Database<T extends DatabaseSettings> {
    Connection connect(T settings) throws NoConnectionException;

    default <P, R> R query(Returnable<P, R> returnable, P passable, T settings) {
        try (Connection connection = this.connect(settings)) {
            return returnable.execute(passable, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
