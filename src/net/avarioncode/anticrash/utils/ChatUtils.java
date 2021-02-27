package net.avarioncode.anticrash.utils;

import org.bukkit.ChatColor;

public final class ChatUtils {
    public static String fixColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«"));
    }
}
