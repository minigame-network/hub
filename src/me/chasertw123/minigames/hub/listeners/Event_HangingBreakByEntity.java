package me.chasertw123.minigames.hub.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

/**
 * Created by Chase on 8/6/2017.
 */
public class Event_HangingBreakByEntity implements Listener {

    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
        if (e.getEntity().getType() == EntityType.PAINTING || e.getEntity().getType() == EntityType.ITEM_FRAME)
            e.setCancelled(true);
    }
}
