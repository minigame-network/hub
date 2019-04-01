package me.chasertw123.minigames.hub.features.npcs;

import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPCData {

    private List<UUID> playersAroundNpc;
    private List<ArmorStand> holographicDisplays;

    NPCData() {
        this.playersAroundNpc = new ArrayList<>();
        this.holographicDisplays = new ArrayList<>();
    }

    public List<ArmorStand> getHolographicDisplays() {
        return holographicDisplays;
    }

    public List<UUID> getPlayersAroundNpc() {
        return playersAroundNpc;
    }
}
