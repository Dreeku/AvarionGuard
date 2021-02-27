package net.avarioncode.anticrash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.nio.charset.StandardCharsets;

public class SignChangeListener implements Listener {
    @EventHandler(
            priority = EventPriority.LOWEST,
            ignoreCancelled = true
    )
    public void onSignChange(SignChangeEvent event) {
        String[] var2 = event.getLines();

        for (String line : var2) {
            if (line.length() >= 46) {
                event.setCancelled(true);
                return;
            }

            if (line.getBytes(StandardCharsets.UTF_8).length > 34) {
                event.setCancelled(true);
                return;
            }
        }

    }
}
