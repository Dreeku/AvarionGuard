package net.avarioncode.anticrash.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerMoveListener implements Listener {
    private boolean nullChunk;

    public boolean isNullChunk() {
        return nullChunk;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent event) {
        if (!isNullChunk()) {
            return;
        }

        final Location to = event.getTo();
        final World world = to.getWorld();
        final Chunk chunk = to.getChunk();

        if (chunk == null || !world.isChunkLoaded(chunk)) {
            event.setCancelled(true);
        }
    }
}