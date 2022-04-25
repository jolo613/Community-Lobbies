package me.x1xx.lib.database.imp;

import me.x1xx.lib.database.Database;
import me.x1xx.lib.database.DatabaseSettings;
import me.x1xx.lib.database.exception.NoConnectionException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteDatabase implements Database<DatabaseSettings> {
    private final DatabaseSettings settings;
    private final String url;

    public SQLiteDatabase(DatabaseSettings settings) {
        this.settings = settings;
        File file = new File(new File(this.getClass().getProtectionDomain()
                .getCodeSource().getLocation().getPath()).getParentFile().getAbsolutePath(), "/data/" + settings.database + ".db");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.url = "jdbc:sqlite:" + file.toURI();

    }

    @Override
    public Connection connect(DatabaseSettings settings) throws NoConnectionException {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:"
                    + settings.host + "/" + settings.database);
        } catch (Exception e) {
            throw new NoConnectionException(e.getClass().getName() + ": " + e.getMessage());
        }
        return c;
    }
}
