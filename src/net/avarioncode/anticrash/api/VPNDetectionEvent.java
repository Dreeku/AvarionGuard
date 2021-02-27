package net.avarioncode.anticrash.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class VPNDetectionEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final String playerName;
    private final UUID uuid;
    private final String ip;

    public VPNDetectionEvent(String player, UUID uuid, String ip) {
        this.playerName = player;
        this.uuid = uuid;
        this.ip = ip;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public String getPlayer() {
        return this.playerName;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getIp() {
        return this.ip;
    }
}
