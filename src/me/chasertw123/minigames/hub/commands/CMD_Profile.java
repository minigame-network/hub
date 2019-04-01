package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.user.OfflineUser;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.guis.profile.GUI_Profile;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/14/2018.
 */
public class CMD_Profile implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use that command!");
            return true;
        }

        User user = Main.getUserManager().get(((Player) sender).getUniqueId());

        if (args.length == 0) {
            new GUI_Profile(user);
            return true;
        }

        if (args.length > 1) {
            user.getPlayer().sendMessage(ChatColor.RED + "Correct Usage: /profile <player>");
            return true;
        }

        OfflineUser opp = CoreAPI.getUser(args[0]);
        if (opp == null) {
            user.getPlayer().sendMessage(ChatColor.RED + "Invalid Player!");
            return true;
        }

        if (opp.getUUID().equals(user.getUuid())) {
            new GUI_Profile(user);
            return true;
        }

        new GUI_Profile(user.getCoreUser(), opp);
        return true;
    }
}
