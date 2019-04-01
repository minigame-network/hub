package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.hub.features.guis.GUI_ServerSelector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/14/2018.
 */
public class CMD_ServerSelector implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use that command!");
            return true;
        }

        new GUI_ServerSelector(CoreAPI.getUser((Player) sender));
        return true;
    }
}
