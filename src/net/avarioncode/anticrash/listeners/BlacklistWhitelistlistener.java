package net.avarioncode.anticrash.listeners;

import net.avarioncode.anticrash.AvarionGuard;
import net.avarioncode.anticrash.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class BlacklistWhitelistlistener implements Listener {
    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onjoinn(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        String playerNAME = player.getName();
        String playerIP = e.getAddress().getHostName();
        if (AvarionGuard.getInstance().getConfig().getBoolean("systems.whitelist")) {
            if (AvarionGuard.getInstance().getConfig().getStringList("whitelist.nickname").contains(playerNAME)) {
                e.allow();
                return;
            }

            if (AvarionGuard.getInstance().getConfig().getStringList("whitelist.ip").contains(playerIP)) {
                e.allow();
                return;
            }
        }

        if (AvarionGuard.getInstance().getConfig().getBoolean("systems.blacklist")) {
            if (AvarionGuard.getInstance().getConfig().getStringList("blacklist.nickname").contains(playerNAME)) {
                e.disallow(Result.KICK_OTHER, ChatUtils.fixColor("&dAvarionGuard &8>> &5You are blacklisted from joining this server!"));
            } else if (AvarionGuard.getInstance().getConfig().getStringList("blacklist.ip").contains(playerIP)) {
                e.disallow(Result.KICK_OTHER, ChatUtils.fixColor("&dAvarionGuard &8>> &5You are blacklisted from joining this server!"));
            } else {
                e.allow();
            }
        }

    }
}
