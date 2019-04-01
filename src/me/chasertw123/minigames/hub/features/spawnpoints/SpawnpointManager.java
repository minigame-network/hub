package me.chasertw123.minigames.hub.features.spawnpoints;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Chase on 1/13/2018.
 */
public class SpawnpointManager {

    private File spawnpointsFile;
    private FileConfiguration spawnpointsYAML;

    private Location hubSpawnpoint = null;
    private HashMap<ServerGameType, Location> spawnpoints;

    public SpawnpointManager() {

        spawnpointsFile = new File(Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "spawnpoints.yml");

        try {
            new File(Main.getInstance().getDataFolder().getAbsolutePath()).mkdirs();
            spawnpointsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        spawnpointsYAML = YamlConfiguration.loadConfiguration(spawnpointsFile);

        if (spawnpointsYAML.contains("Main Spawnpoint")) {
            hubSpawnpoint = this.deserializeLoc(spawnpointsYAML.getString("Main Spawnpoint"));
            this.hubSpawnpoint.getWorld().setDifficulty(Difficulty.EASY);
            this.hubSpawnpoint.getWorld().setGameRuleValue("doMobSpawning", "false");
            this.hubSpawnpoint.getWorld().setGameRuleValue("doDaylightCycle", "false");
            this.hubSpawnpoint.getWorld().setTime(6000);
        }

        spawnpoints = new HashMap<>();
        for (ServerGameType sgt : ServerGameType.values())
            spawnpoints.put(sgt, spawnpointsYAML.contains(sgt.getDisplay() + " Spawnpoint") ? this.deserializeLoc(spawnpointsYAML.getString(sgt.getDisplay() + " Spawnpoint")) : null);
    }

    public Location getHubSpawnpoint() {
        Location location = hubSpawnpoint == null ? Bukkit.getServer().getWorlds().get(0).getSpawnLocation().clone() : hubSpawnpoint.clone();
        return location.add(ThreadLocalRandom.current().nextDouble(-1, 1), 0.0D, ThreadLocalRandom.current().nextDouble(-1, 1));
    }

    public void setHubSpawnpoint(Location location) {
        this.hubSpawnpoint = location;
        this.spawnpointsYAML.set("Main Spawnpoint", this.serializeLoc(location));
        this.saveConfig();
    }

    public Location getGameTypeSpawnpoint(ServerGameType serverGameType) {
        return spawnpoints.get(serverGameType) != null ? spawnpoints.get(serverGameType).clone()
                .add(ThreadLocalRandom.current().nextDouble(-1, 1), 0.0D, ThreadLocalRandom.current().nextDouble(-1, 1)) : getHubSpawnpoint();
    }

    public void setGameTypeSpawnpoint(ServerGameType serverGameType, Location location) {
        this.spawnpoints.put(serverGameType, location);
        this.spawnpointsYAML.set(serverGameType.getDisplay() + " Spawnpoint", this.serializeLoc(location));
        this.saveConfig();
    }

    private void saveConfig() {
        try {
            spawnpointsYAML.save(spawnpointsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeLoc(Location l) {
        return l.getWorld().getName() + ", " + l.getX() + ", " + l.getY() + ", " + l.getZ() + ", " + l.getYaw() + ", " + l.getPitch();
    }

    private Location deserializeLoc(String s) {
        String[] st = s.split(", ");
        return new Location(Bukkit.getWorld(st[0]), Double.parseDouble(st[1]), Double.parseDouble(st[2]), Double.parseDouble(st[3]), Float.parseFloat(st[4]), Float.parseFloat(st[5]));
    }

}
