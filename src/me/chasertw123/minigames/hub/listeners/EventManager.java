package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.listeners.packets.Event_UseEntity;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * Created by Chase on 1/6/2018.
 */
public class EventManager {

    public EventManager() {

        new Event_UseEntity();

        Listener[] listeners = {
                new Event_BoosterUpdateEvent(),
                new Event_EntityDamage(),
                new Event_EntityInteract(),
                new Event_HangingBreakByEntity(),
                new Event_PlayerChatEvent(),
                new Event_PlayerInteract(),
                new Event_PlayerInteractEntity(),
                new Event_PlayerJoin(),
                new Event_PlayerMove(),
                new Event_PlayerQuit(),
                new Event_PlayerRankStatusUpdate(),
                new Event_PlayerToggleSetting(),
                new Event_WeatherChange()
        };

        for (Listener listener : listeners)
            Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
    }

}
