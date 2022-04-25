package me.x1xx.settings;

import me.x1xx.lib.database.DatabaseSettings;
import me.x1xx.lib.database.DatabaseType;

/**
 * Settings for the application.
 */
public class Settings {
    public String token;
    public DiscordStatus status = new DiscordStatus();
    public DatabaseSettings TMS_DATABASE = new DatabaseSettings(DatabaseType.MYSQL,
            false,"172.18.0.1", "3306", "tms", "communitylobby", "");
    public DatabaseSettings COMMUNITY_DATABASE = new DatabaseSettings(DatabaseType.MYSQL,
            true, "172.18.0.1", "3306", "communitylobby", "communitylobby", "");
    public String moderatorRole = "967268741861756960";
    public String hostRole = "967292885928144947";
    public String guildID = "967268741828214855";
}
