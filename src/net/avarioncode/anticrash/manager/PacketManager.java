package net.avarioncode.anticrash.manager;

import io.netty.channel.Channel;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PacketManager {
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent paramPlayerPickupItemEvent, Channel channel, Player sender) {
        Item item = paramPlayerPickupItemEvent.getItem();
        if (item == null)
            return;
        if (item.getPickupDelay() > 0) {
            paramPlayerPickupItemEvent.setCancelled(true);
            item.remove();
            return;
        }
        if (item.getLocation() == null) {
            paramPlayerPickupItemEvent.setCancelled(true);
            item.remove();
            PlayerManager.DisconnectPacket(sender, "&8[&dItemsFix&8] &5Too many packets!");
            channel.close();
            return;
        }
        if (item.getItemStack().getAmount() > 64 || item.getItemStack().getAmount() < 0) {
            paramPlayerPickupItemEvent.setCancelled(true);
            item.remove();
            PlayerManager.DisconnectPacket(sender, "&8[&dItemsFix&8] &5Too many packets!");
            channel.close();
        }
    }

    @EventHandler
    public void onChat(PlayerCommandPreprocessEvent paramPlayerCommandPreprocessEvent) {
        boolean bool = false;
        String str = paramPlayerCommandPreprocessEvent.getMessage();
        char[] arrayOfChar;
        int i = (arrayOfChar = str.toCharArray()).length;
        for (byte b = 0; b < i; b++) {
            char c = arrayOfChar[b];
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == ':' || c == ',' || c == '.' || c == '\u00b3' || c == '\u00b2' || c == '\'' || c == '*' || c == '+' || c == '~' || c == '-' || c == '|' || c == '>' || c == '<' || c == '^' || c == '?' || c == '=' || c == ')' || c == '(' || c == '%' || c == '$' || c == '\"' || c == '!' || c == '&' || c == ' ' || c == '/' || c == '\u00fc' || c == '\u00f6' || c == '\u00e4' || c == '_' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0' || c == '#') {
                paramPlayerCommandPreprocessEvent.setCancelled(true);
                bool = true;
            }
            if (bool) {
                paramPlayerCommandPreprocessEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent, Channel channel, Player sender) {
        Player player = playerMoveEvent.getPlayer();
        if (playerMoveEvent.getFrom().distance(playerMoveEvent.getTo()) < 0.0) {
            player.teleport(playerMoveEvent.getFrom());
            PlayerManager.DisconnectPacket(sender, "&8[&dMoveFix&8] &5Too many packets!");
            channel.close();
            return;
        }
        if (playerMoveEvent.getFrom().distance(playerMoveEvent.getTo()) > 18.0) {
            player.teleport(playerMoveEvent.getFrom());
            PlayerManager.DisconnectPacket(sender, "&8[&dMoveFix&8] &5Too many packets!");
            channel.close();
        }
    }
}