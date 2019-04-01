package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.user.data.settings.Setting;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/14/2018.
 */
public class CMD_Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use that command!");
            return true;
        }

        User pp = CoreAPI.getUser((Player) sender);

        if (!pp.isDeluxe()) {
            pp.sendPrefixedMessage(ChatColor.RED + "You need have " + ChatColor.GOLD + "DELUXE" + ChatColor.RED +  " to be able to fly!");
            return true;
        }

        if (pp.getPlayer().getAllowFlight()) {
            pp.sendMessage(ChatColor.GREEN + "Flight: " + ChatColor.WHITE + "Disabled");
            pp.setSetting(Setting.AUTO_FLY, false);
            pp.getPlayer().setAllowFlight(false);
            return true;
        }

        else {
            pp.sendMessage(ChatColor.GREEN + "Flight: " + ChatColor.WHITE + "Enabled");
            pp.setSetting(Setting.AUTO_FLY, true);
            pp.getPlayer().setAllowFlight(true);
            return true;
        }
    }
}
