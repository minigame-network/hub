package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/14/2018.
 */
public class CMD_Warp implements CommandExecutor {

    private static final String[] HUB_SPAWNPOINT_NAMES= {"main", "hub", "lobby", "center"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use that command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "You need to input one game to teleport to.");
            return true;
        }

        ServerGameType gameType = null;
        for (ServerGameType sgt : ServerGameType.values())
            if (args[0].equalsIgnoreCase(sgt.toString())) {
                gameType = sgt;
                break;
            }

        boolean hubSpawn = false;
        for (String s : HUB_SPAWNPOINT_NAMES)
            if (s.equalsIgnoreCase(args[0])) {
                hubSpawn = true;
                break;
            }

        if (gameType == null && !hubSpawn) {
            player.sendMessage(ChatColor.RED + args[0] + " is not a valid warp location.");
            return true;
        }

        if (hubSpawn) {
            player.teleport(Main.getSpawnpointManager().getHubSpawnpoint());
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 1F);
            return true;
        }

        else {
            player.teleport(Main.getSpawnpointManager().getGameTypeSpawnpoint(gameType));
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 1F);
            return true;
        }
    }

}
