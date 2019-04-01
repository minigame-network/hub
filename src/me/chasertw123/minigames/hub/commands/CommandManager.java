package me.chasertw123.minigames.hub.commands;

import me.chasertw123.minigames.hub.Main;

/**
 * Created by Chase on 1/6/2018.
 */
public class CommandManager {

    public CommandManager() {
        Main.getInstance().getCommand("Fly").setExecutor(new CMD_Fly());
        Main.getInstance().getCommand("Profile").setExecutor(new CMD_Profile());
        Main.getInstance().getCommand("ServerSelector").setExecutor(new CMD_ServerSelector());
        Main.getInstance().getCommand("SetSpawnpoint").setExecutor(new CMD_SetSpawnpoint());
        Main.getInstance().getCommand("Shop").setExecutor(new CMD_Shop());
        Main.getInstance().getCommand("Warp").setExecutor(new CMD_Warp());
    }

}
