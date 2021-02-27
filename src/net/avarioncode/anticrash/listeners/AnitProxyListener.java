package net.avarioncode.anticrash.listeners;

import net.avarioncode.anticrash.AvarionGuard;
import net.avarioncode.anticrash.api.PCDetection;
import net.avarioncode.anticrash.api.VPNDetectionEvent;
import net.avarioncode.anticrash.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class AnitProxyListener implements Listener {
    AvarionGuard main;

    public AnitProxyListener(AvarionGuard main) {
        this.main = main;
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void preLogin(AsyncPlayerPreLoginEvent e) {
        if (AvarionGuard.getInstance().getConfig().getBoolean("systems.antiproxy")) {
            String ip = e.getAddress().getHostAddress();
            PCDetection pc = new PCDetection(null);
            pc.useSSL();
            pc.set_api_timeout(5000);
            pc.setUseVpn(true);

            try {
                pc.parseResults(ip);
            } catch (Exception ignored) {
            }

            if (pc.status.equalsIgnoreCase("ok")) {
                if (pc.proxy.contains("yes")) {
                    Bukkit.getServer().getPluginManager().callEvent(new VPNDetectionEvent(e.getName(), e.getUniqueId(), ip));
                    Bukkit.getServer().banIP(ip);
                    e.setKickMessage(ChatUtils.fixColor("&aAvarionGuard &8>> &2Proxy connection detected!"));
                    e.setLoginResult(Result.KICK_OTHER);
                }
            } else if (pc.status.equalsIgnoreCase("warning")) {
                System.out.println("Status: " + pc.status);
                System.out.println("WARNING!!! " + pc.message);
                if (pc.proxy.contains("yes")) {
                    Bukkit.getServer().getPluginManager().callEvent(new VPNDetectionEvent(e.getName(), e.getUniqueId(), ip));
                    e.setKickMessage(ChatUtils.fixColor("&aAvarionGuard &8>> &2Proxy connection detected!"));
                    e.setLoginResult(Result.KICK_OTHER);
                }
            } else if (pc.status.equalsIgnoreCase("denied") || pc.status.equalsIgnoreCase("error")) {
                System.out.println("Status: " + pc.status);
                System.out.println("ERROR!!! " + pc.message);
            }
        } else {
            Bukkit.getLogger().info("AntiProxy is disabled!");
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("#AvarionGuard")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatUtils.fixColor("&8>> &5This server is protected by &dAvarionGuard " + AvarionGuard.wersja));
            e.getPlayer().sendMessage(ChatUtils.fixColor("&8>> &5One of better &dAntiCrash &5and &dAntiProxy &5system"));
        }
    }
}
