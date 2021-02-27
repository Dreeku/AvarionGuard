package net.avarioncode.anticrash;

import net.avarioncode.anticrash.commands.AvarionGuardCommand;
import net.avarioncode.anticrash.listeners.*;
import net.avarioncode.anticrash.manager.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class AvarionGuard extends JavaPlugin {
    private static AvarionGuard instance;
    public static String wersja = "v0.1-ALPHA";
    public static Map<Player, AntiExploit> indzektor = new HashMap<>();


    public void onEnable() {
        try {
            instance = this;
            this.saveDefaultConfig();
            RegisterEvents();
            RegisterCommands();
            new PacketManager();

            for (Player p : this.getServer().getOnlinePlayers()) {
                AntiExploit pr = new AntiExploit(p, p.getName()) {
                };
                pr.inject();
                indzektor.put(p, pr);
            }
        } catch (Throwable var7) {
            var7.printStackTrace();
        }

    }

    public void RegisterCommands() {
        this.getCommand("avarionguard").setExecutor(new AvarionGuardCommand());
    }

    public void RegisterEvents() {
        this.getServer().getPluginManager().registerEvents(new AnitProxyListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ClickGuiListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerBookEditListener(), this);
        this.getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockedCommandListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlacklistWhitelistlistener(), this);
        this.getServer().getPluginManager().registerEvents(new OnQuitPlayerListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
    }

    public static AvarionGuard getInstance() {
        return instance;
    }

    public void onDisable() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            indzektor.get(p).uninject();
            indzektor.remove(p);
        }

    }
}
