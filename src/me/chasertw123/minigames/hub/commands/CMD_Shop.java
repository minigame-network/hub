package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.shops.Shop;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/6/2018.
 */
public class CMD_Shop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use that command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Command Usage: /shop <game>");
            return true;
        }

        ServerGameType gameType = null;
        for (ServerGameType sgt : ServerGameType.values())
            if (args[0].equalsIgnoreCase(sgt.toString())) {
                gameType = sgt;
                break;
            }

        if (gameType == null) {
            player.sendMessage(ChatColor.RED + "No shop found for: " + args[0]);
            return true;
        }

        Shop shop;
        try {
            shop = Shop.valueOf(gameType.toString());
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "A shop is currently not available for " + gameType.getDisplay() + ".");
            Bukkit.getLogger().severe("No shop created for: " + gameType.getDisplay());
            return true;
        }

        User user = Main.getUserManager().get(player.getUniqueId());
        if (!user.isLoaded()) {
            player.sendMessage(ChatColor.BLUE + "Please try again shortly. We are still loading your unlocks.");
            return true;
        }

        shop.openShop(player, 0);
        return true;
    }

}
