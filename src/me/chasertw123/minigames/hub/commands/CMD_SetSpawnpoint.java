package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import me.chasertw123.minigames.shared.rank.RankType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/14/2018.
 */
public class CMD_SetSpawnpoint implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use that command!");
            return true;
        }

        User pp = CoreAPI.getUser((Player) sender);

        if (pp.getRank().getRankType() != RankType.UPPERSTAFF) {
            pp.getPlayer().performCommand("help");
            return true;
        }

        String validSpawnpoints = "Valid Spawnpoints: main";
        for (ServerGameType serverGameType : ServerGameType.values())
            validSpawnpoints = validSpawnpoints.concat(", " + serverGameType.toString().toLowerCase());

        if (args.length != 1) {
            pp.sendMessage(ChatColor.RED + "Must include valid spawnpoint you want to set.");
            pp.sendMessage(ChatColor.RED + validSpawnpoints);
            return true;
        }

        ServerGameType gameType = null;
        for (ServerGameType sgt : ServerGameType.values())
            if (args[0].equalsIgnoreCase(sgt.toString())) {
                gameType = sgt;
                break;
            }

        if (gameType == null && !args[0].equalsIgnoreCase("main")) {
            pp.sendMessage(ChatColor.RED + "Invalid spawnpoint selected: " + args[0]);
            pp.sendMessage(ChatColor.RED + validSpawnpoints);
            return true;
        }

        if (gameType != null) {
            Main.getSpawnpointManager().setGameTypeSpawnpoint(gameType, pp.getPlayer().getLocation().clone().add(0.0D, 0.33D, 0.0D));
            pp.sendMessage(ChatColor.GREEN + "Set " + gameType.getDisplay() + " spawnpoint for hub to your current location.");
            return true;

        }

        else {
            Main.getSpawnpointManager().setHubSpawnpoint(pp.getPlayer().getLocation().clone().add(0.0D, 0.33D, 0.0D));
            pp.sendMessage(ChatColor.GREEN + "Set main spawnpoint for hub to your current location.");
            return true;
        }
    }
}
