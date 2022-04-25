package me.x1xx.listener;

import me.x1xx.connector.TMSConnector;
import me.x1xx.lib.Config;
import me.x1xx.settings.Settings;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Gives the host role when required to new members.
 */
public class HostListener extends ListenerAdapter {
    private final Config<Settings> config;
    private final TMSConnector connector;

    public HostListener(Config<Settings> config, TMSConnector connector) {
        this.config = config;
        this.connector = connector;
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if (event.getGuild().getId().equals(config.getConfiguration().guildID)) {
            if (connector.isDiscordAuthenticated(event.getMember().getId())) {
                event.getGuild().addRoleToMember(event.getMember(), Objects.requireNonNull(event.getGuild().getRoleById(config.getConfiguration().hostRole))).queue();
            }
        }
    }
}
