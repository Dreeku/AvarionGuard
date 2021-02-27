package net.avarioncode.anticrash.commands;

import net.avarioncode.anticrash.AvarionGuard;
import net.avarioncode.anticrash.utils.ChatUtils;
import net.avarioncode.anticrash.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AvarionGuardCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("avarionguard.reload")) {
            sender.sendMessage(ChatUtils.fixColor("&8>> &5This server is protected by &dAvarionGuard " + AvarionGuard.wersja));
            sender.sendMessage(ChatUtils.fixColor("&8>> &5One of better &dAntiCrash &5and &dAntiProxy &5system"));
            return true;
        }

        if (AvarionGuard.getInstance().getConfig().getBoolean("commands.avarionguicommand")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                menu(p);
            } else {
                sender.sendMessage(ChatUtils.fixColor("&8>> &5You are not a player!"));
            }
        } else {
            if (args.length == 0) {
                if (sender.hasPermission("avarionguard.reload")) {
                    sender.sendMessage(ChatUtils.fixColor("&8>> &5Correct usage: &d/avarionguard [reload]"));
                }

                return false;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("avarionguard.reload")) {
                AvarionGuard.getInstance().reloadConfig();
                sender.sendMessage(ChatUtils.fixColor("&8>> &5Successfully reloaded config!"));
            }
        }

        return false;
    }


    public static void menu(Player p) {
        Inventory inventory = Bukkit.createInventory(p, 27, ChatUtils.fixColor("&8>> &dAvarionGuard"));
        ItemBuilder wersja = (new ItemBuilder(Material.SIGN)).setTitle(ChatUtils.fixColor("&5About: ")).addLore(ChatUtils.fixColor("&8>> &5Version: &d" + AvarionGuard.wersja)).addLore(ChatUtils.fixColor("&8>> &5Discord:&d discord.gg/9JQMDXfp4E"));
        ItemBuilder reload = (new ItemBuilder(Material.STICK)).setTitle(ChatUtils.fixColor("&5Reload Config: ")).addLore(ChatUtils.fixColor("&8>> &5Click to reload &dconfig.yml&5!"));
        ItemBuilder creators = (new ItemBuilder(Material.COMMAND)).setTitle(ChatUtils.fixColor("&5Creators: ")).addLore(ChatUtils.fixColor("&8>> &5AvarionCode: ")).addLore(ChatUtils.fixColor("&7 - &dDreeku")).addLore(ChatUtils.fixColor("&7 - &dKielczi"));
        inventory.setItem(10, reload.build());
        inventory.setItem(13, wersja.build());
        inventory.setItem(16, creators.build());
        p.openInventory(inventory);
    }
}
