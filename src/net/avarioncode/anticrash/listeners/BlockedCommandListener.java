package net.avarioncode.anticrash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandListener implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String c = e.getMessage().toLowerCase();
        if (c.startsWith("//calc") || c.startsWith("//eval") || c.startsWith("//evaluate") || c.startsWith("/calculate") || c.startsWith("//solve") || c.startsWith("/worldedit:/calc") || c.startsWith("/worldedit:/eval") || c.startsWith("/worldedit:/solve")) {
            e.setCancelled(true);
        }

    }
}
