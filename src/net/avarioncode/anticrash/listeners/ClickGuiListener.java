package net.avarioncode.anticrash.listeners;

import net.avarioncode.anticrash.AvarionGuard;
import net.avarioncode.anticrash.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickGuiListener implements Listener {
    @EventHandler(
            priority = EventPriority.MONITOR
    )
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getName().equalsIgnoreCase(ChatUtils.fixColor("&8>> &dAvarionGuard"))) {
            if (e.getSlot() == 10) {
                AvarionGuard.getInstance().reloadConfig();
                p.sendMessage(ChatUtils.fixColor("&8>> &5Successfully reloaded config!"));
                e.setCancelled(true);
            }

            e.setCancelled(true);
        }

    }
}
