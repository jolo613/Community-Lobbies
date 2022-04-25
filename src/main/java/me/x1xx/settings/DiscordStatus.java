package me.x1xx.settings;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordStatus {
    public Activity.ActivityType type = Activity.ActivityType.COMPETING;
    public String status = "with TMS | https://tms.to/community";
    public String url = "https://tms.to/community";

    public DiscordStatus(Activity.ActivityType type, String status, String url) {
        this.type = type;
        this.status = status;
        this.url = url;
    }

    public DiscordStatus() {

    }

    public void loadActivity(JDA jda) {
        jda.getPresence().setActivity(Activity.of(type, status, url));
    }
}
