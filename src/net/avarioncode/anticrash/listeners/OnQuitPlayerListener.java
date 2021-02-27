package net.avarioncode.anticrash.listeners;

import net.avarioncode.anticrash.AntiExploit;
import net.avarioncode.anticrash.AvarionGuard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuitPlayerListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        AvarionGuard.indzektor.get(e.getPlayer()).uninject();
        AvarionGuard.indzektor.remove(e.getPlayer());
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        AntiExploit pr = new AntiExploit(e.getPlayer(), e.getPlayer().getName()) {
        };
        pr.inject();
        AvarionGuard.indzektor.put(e.getPlayer(), pr);
    }
}
