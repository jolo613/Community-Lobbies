package me.x1xx.lib.database.exception;

/**
 * An error that occurs when there is no connection to the database.
 */
public class NoConnectionException extends Exception {
    public NoConnectionException(String message) {
        super(message);
    }
}
