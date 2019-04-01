package me.chasertw123.minigames.hub.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class Event_EntityInteract implements Listener {

    @EventHandler
    public void onEntityInteract(EntityInteractEvent e) {

        if (e.getBlock().getType() == Material.SOIL && !(e.getEntity() instanceof Player))
            e.setCancelled(true);

    }
}
