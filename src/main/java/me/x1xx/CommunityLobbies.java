package me.x1xx;

import me.x1xx.connector.TMSConnector;
import me.x1xx.lib.Config;
import me.x1xx.lib.ConfigManager;
import me.x1xx.lib.FileWatcher;
import me.x1xx.lib.WrappedObject;
import me.x1xx.listener.HostListener;
import me.x1xx.settings.Settings;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

/**
 * Main class for community lobbies.
 */
public class CommunityLobbies {
    private final Config<Settings> config;
    private final JDA JDA;

    public CommunityLobbies() throws LoginException {
        config = new ConfigManager().loadConfig("/data/config.json", new Settings(), this.getClass());

        FileWatcher<WrappedObject<Config<Settings>, CommunityLobbies>> fileWatcher = new FileWatcher<>(config.getConfigurationFile(),
                (config) -> {
                    System.out.println("Config file changed, reloading...");
                    config.getFirst().reloadConfig();
                    config.getSecond().reloadEvent();


                }, new WrappedObject<>(config, this));

        fileWatcher.start();
        TMSConnector tmsConnector = new TMSConnector(config.getConfiguration().TMS_DATABASE);


        this.JDA = JDABuilder.createDefault(config.getConfiguration().token)
                        .

                enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS
                )
                        .

                setChunkingFilter(ChunkingFilter.NONE)
                        .

                setMemberCachePolicy(MemberCachePolicy.DEFAULT)
                .addEventListeners(new HostListener(config, tmsConnector))
                        .
                build();


    }

    public static void main(String[] args) throws LoginException {
        new CommunityLobbies();
    }

    public void reloadEvent() {
        config.getConfiguration().status.loadActivity(JDA);
    }
}
