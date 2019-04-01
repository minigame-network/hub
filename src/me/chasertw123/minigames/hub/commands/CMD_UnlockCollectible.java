package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.shared.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/16/2018.
 */
public class CMD_UnlockCollectible implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            User paradisePlayer = CoreAPI.getUser((Player) sender);
            if (paradisePlayer.getRank() != Rank.DEV)
                return true;
        }

        if (args.length == 1) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Console Usage: /UnlockCollectible <CollectibleID> <Player>");
                return true;
            }

//            User paradisePlayer = ParadiseCore.getAPI().getParadisePlayer((Player) sender);
            return true;
        }

        return true;
    }
}
