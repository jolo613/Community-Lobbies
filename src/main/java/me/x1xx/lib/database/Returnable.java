package me.x1xx.lib.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A returnable is a function that can be executed with a passable and returns a value.
 * @param <T> The type of the passable.
 * @param <V> The type of the value returned.
 */
public interface Returnable<T, V> {
    V execute(T t, Connection connection) throws Exception;
}
