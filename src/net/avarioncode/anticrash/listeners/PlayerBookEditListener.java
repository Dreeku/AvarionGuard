package net.avarioncode.anticrash.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

public class PlayerBookEditListener implements Listener {
    @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
    )
    public void onEvent(PlayerEditBookEvent playerEditBookEvent) {
        Player player = playerEditBookEvent.getPlayer();
        BookMeta newBookMeta = playerEditBookEvent.getNewBookMeta();
        if (newBookMeta != null && !newBookMeta.getAuthor().equalsIgnoreCase(player.getName())) {
            newBookMeta.setAuthor(player.getName());
        }

    }
}
