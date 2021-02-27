package net.avarioncode.anticrash.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {
    private boolean selfDamage;

    public boolean isSelfDamage() {
        return selfDamage;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (!isSelfDamage()) {
            return;
        }

        final Entity entity = event.getEntity();

        if (entity instanceof Player) {
            final Entity damager = event.getDamager();

            if (damager instanceof Player && entity == damager) {
                event.setCancelled(true);
            }
        }
    }
}
