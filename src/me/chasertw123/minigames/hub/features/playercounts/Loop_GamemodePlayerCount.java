package me.chasertw123.minigames.hub.features.playercounts;

import com.mongodb.Block;
import com.mongodb.client.MongoCursor;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.npcs.NPC;
import me.chasertw123.minigames.hub.features.npcs.NPCData;
import me.chasertw123.minigames.hub.features.npcs.NPCType;
import me.chasertw123.minigames.shared.database.Database;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Loop_GamemodePlayerCount extends BukkitRunnable {

    private Map<ServerGameType, Integer> playerCounts;

    public Loop_GamemodePlayerCount() {
        playerCounts = new HashMap<>();

        this.runTaskTimer(Main.getInstance(), 40L, 20 * 5);
    }

    @Override
    public void run() {
        // Download the data
        Database database = me.chasertw123.minigames.core.Main.getMongoDatabase();

        database.getMongoCollection(Database.Collection.PLAYER_COUNTS).find().forEach((Block<Document>) item ->
            playerCounts.put(ServerGameType.valueOf(item.getString("serverType")), item.getInteger("playerCount")));

        // Update the NPCS
        updateForMinigame(NPCType.SPLEGG, ServerGameType.SPLEGG);
        updateForMinigame(NPCType.WATER_WARS, ServerGameType.WATER_WARS);
        updateForMinigame(NPCType.EVOLUTION, ServerGameType.EVOLUTION);
    }

    private void updateForMinigame(NPCType type, ServerGameType gameType) {
        Map.Entry<NPC, NPCData> npc = Main.getNPCManager().getNpc(type);

        npc.getValue().getHolographicDisplays().forEach(hd -> {
            if(ChatColor.stripColor(hd.getCustomName()).contains("Playing")) {
                // Update this one
                hd.setCustomName(ChatColor.AQUA + "" + playerCounts.get(gameType) + " Playing");
            }
        });
    }

    public Map<ServerGameType, Integer> getPlayerCounts() {
        return playerCounts;
    }
}
