package me.chasertw123.minigames.hub;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.features.chests.Chest;
import me.chasertw123.minigames.core.features.chests.ChestType;
import me.chasertw123.minigames.hub.commands.CommandManager;
import me.chasertw123.minigames.hub.features.npcs.NPCData;
import me.chasertw123.minigames.hub.features.npcs.NPCManager;
import me.chasertw123.minigames.hub.features.parkour.CourseManager;
import me.chasertw123.minigames.hub.features.playercounts.Loop_GamemodePlayerCount;
import me.chasertw123.minigames.hub.features.spawnpoints.SpawnpointManager;
import me.chasertw123.minigames.hub.listeners.EventManager;
import me.chasertw123.minigames.hub.user.UserManager;
import me.chasertw123.minigames.shared.database.Database;
import me.chasertw123.minigames.shared.framework.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Chase on 1/5/2018.
 */
public class Main extends JavaPlugin {

    private static Main plugin;
    private static UserManager userManager;
    private static SpawnpointManager spawnpointManager;
    private static CourseManager courseManager;
    private static NPCManager npcManager;
    private static TaskChainFactory taskChainFactory;

    @Override
    public void onLoad() {
        CoreAPI.getServerDataManager().setServerType(ServerType.HUB);
        CoreAPI.getServerDataManager().setDefaultGamemode(GameMode.ADVENTURE);
    }

    @Override
    public void onEnable() {
        plugin = this;

        spawnpointManager = new SpawnpointManager();
        courseManager = new CourseManager();
        userManager = new UserManager();
        npcManager = new NPCManager();
        taskChainFactory = BukkitTaskChainFactory.create(this);

        new Loop_GamemodePlayerCount();
//        new Loop_AutoMessage();
        new EventManager();
        new CommandManager();

        // Register a check
//        Chest.addChest(new Chest(new Location(Bukkit.getWorld("world"), ), new Location(Bukkit.getWorld("world"))));
    }

    @Override
    public void onDisable() {
        for(NPCData npcData : npcManager.getNpcs().values())
            npcData.getHolographicDisplays().forEach(ArmorStand::remove);

        plugin = null;
    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }

    public static NPCManager getNPCManager() {
        return npcManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static SpawnpointManager getSpawnpointManager() {
        return spawnpointManager;
    }

    public static CourseManager getCourseManager() {
        return courseManager;
    }

    public static Main getInstance() {
        return plugin;
    }
}
