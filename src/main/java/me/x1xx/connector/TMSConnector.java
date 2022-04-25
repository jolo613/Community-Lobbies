package me.x1xx.connector;

import me.x1xx.lib.database.Database;
import me.x1xx.lib.database.DatabaseSettings;
import me.x1xx.lib.database.exception.NoConnectionException;
import me.x1xx.lib.database.imp.MySQLDatabase;
import me.x1xx.lib.database.imp.SQLiteDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TMSConnector {
    private Database<DatabaseSettings> database;
    private DatabaseSettings settings;

    public TMSConnector(DatabaseSettings settings) {
        this.settings = settings;
        this.database = new MySQLDatabase(settings);
        try {
            database.connect(settings).close();
        } catch (SQLException | NoConnectionException e) {
            System.out.println("Could not connect to mysql database.");
            if (settings.fallbackSqlite) {
                System.out.println("Falling back to sqlite.");
                this.database = new SQLiteDatabase(settings);
            }
        }
    }


    public boolean isDiscordAuthenticated(String id) {
        try {
            return database.query(
                    (connection, Boolean) -> {
                        PreparedStatement statement = connection.prepareStatement("SELECT `identity_id` FROM `discord__user` WHERE `id` = ?;");
                        statement.setString(1, id);
                        ResultSet result = statement.executeQuery();
                        if(result.next()){
                            String value = result.getString(0);
                            if(value == null) return false;
                            int i = Integer.parseInt(value);
                            return isModIDAuthenticated(i);

                        }
                        return false;
                    }, database.connect(settings), settings);
        } catch (NoConnectionException e) {
            e.printStackTrace();
        }
        System.out.println("Error: Could not authenticate user due to error.");
        return false;
    }

    public boolean isModIDAuthenticated(int id) {
        try {
            return database.query(
                    (connection, Boolean) -> {
                        PreparedStatement statement = connection.prepareStatement("SELECT `authenticated` FROM `identity` WHERE `id` = ?;");
                        statement.setInt(1, id);
                        ResultSet result = statement.executeQuery();
                        if(result.next()){
                            return result.getBoolean(1);
                        }
                        return false;
                    }, database.connect(settings), settings);
        } catch (NoConnectionException e) {
            e.printStackTrace();
        }
        System.out.println("Error: Could not authenticate user due to error.");
        return false;
    }

}
