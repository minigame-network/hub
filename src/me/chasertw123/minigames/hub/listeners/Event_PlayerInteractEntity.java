package me.chasertw123.minigames.hub.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Chase on 8/4/2017.
 */
public class Event_PlayerInteractEntity implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {

        if (e.getPlayer().getGameMode() != GameMode.CREATIVE && (e.getRightClicked().getType() == EntityType.ITEM_FRAME
                || e.getRightClicked().getType() == EntityType.PAINTING || e.getRightClicked().getType() == EntityType.ARMOR_STAND))
            e.setCancelled(true);

    }
}
