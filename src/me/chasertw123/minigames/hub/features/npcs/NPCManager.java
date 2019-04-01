package me.chasertw123.minigames.hub.features.npcs;

import com.google.common.collect.Lists;
import me.chasertw123.minigames.hub.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class NPCManager extends BukkitRunnable {

    private Map<NPC, NPCData> npcs = new HashMap<>();

    public NPCManager() {

        HashMap<Location, NPCType> npcSpawns = new HashMap<>();

        npcSpawns.put(new Location(Bukkit.getWorld("world"), -50.5, 80.5, -64.5, 53F, -5.6F), NPCType.MYSTERY_MAN);
        npcSpawns.put(new Location(Bukkit.getWorld("world"), -63.5, 81, -80.5, 0, 0F), NPCType.SPLEGG);
        npcSpawns.put(new Location(Bukkit.getWorld("world"), -59.5, 81, -80.5, 9, 0), NPCType.EVOLUTION);
        npcSpawns.put(new Location(Bukkit.getWorld("world"), -67.5, 81, -80.5, -9, 0), NPCType.WATER_WARS);

        npcSpawns.forEach((location, type) -> {
            NPC npc = new NPC(type, location);
            npcs.put(npc, new NPCData());
            location.getWorld().getNearbyEntities(location, 1.2D, 5.0D, 1.2D).forEach(Entity::remove);

            double yAddition = -0.2D;
            for (String text : Lists.reverse(Arrays.asList(type.getAboveText()))) {

                ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0D, yAddition, 0D), EntityType.ARMOR_STAND);

                as.setVisible(false);
                as.setGravity(false);
                as.setCustomName(text);
                as.setCustomNameVisible(true);

                // Add this ArmorStand
                npcs.get(npc).getHolographicDisplays().add(as);

                yAddition += 0.35;
            }
        });

        this.runTaskTimer(Main.getInstance(), 20L, 10L);
    }

    @Override
    public void run() {
        Main.getUserManager().toCollection().forEach(user ->
                npcs.keySet().forEach(npc -> {
                    if (user.getPlayer().getLocation().distance(npc.getLocation()) <= 48 && !npcs.get(npc).getPlayersAroundNpc().contains(user.getUuid())) {
                        npc.show(user.getPlayer());
                        npcs.get(npc).getPlayersAroundNpc().add(user.getUuid());
                    }

                    else if (user.getPlayer().getLocation().distance(npc.getLocation()) > 48 && npcs.get(npc).getPlayersAroundNpc().contains(user.getUuid())) {
                        npc.destroy(user.getPlayer());
                        npcs.get(npc).getPlayersAroundNpc().remove(user.getUuid());
                    }
                }));
    }

    /** Gets both the NPC and its data **/
    public Map.Entry<NPC, NPCData> getNpc(NPCType type) {
        for(Map.Entry<NPC, NPCData> item : npcs.entrySet())
            if(item.getKey().getNpcType() == type)
                return item;

        return null;
    }

    public Map<NPC, NPCData> getNpcs() {
        return npcs;
    }
}
