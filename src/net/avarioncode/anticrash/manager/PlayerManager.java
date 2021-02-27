package net.avarioncode.anticrash.manager;

import net.avarioncode.anticrash.utils.ChatUtils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerManager {
    public static void DisconnectPacket(Player p, String nieplacz) {
        String MSG;
        String line1 = ChatUtils.fixColor("&dAvarionGuard &8>> &5NOPE!");
        MSG = line1 + "\n" + nieplacz;
        PacketPlayOutKickDisconnect paaa = new PacketPlayOutKickDisconnect(ChatSerializer.a("{text:\"" + MSG + "\"}"));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(paaa);
    }
}
