package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.collectibles.gadgets.Gadget_SheepBomb;
import me.chasertw123.minigames.hub.Main;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Chase on 9/2/2017.
 */
public class Event_EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Main.getUserManager().toCollection().forEach(user -> {

            if (user.getActiveMorph() != null && user.getActiveMorph().getEntity().getUniqueId().equals(event.getEntity().getUniqueId()))
                event.setCancelled(true);

            if (user.getActiveGadget() != null && user.getActiveGadget() instanceof Gadget_SheepBomb)
                for (Entity entity : ((Gadget_SheepBomb) user.getActiveGadget()).getSheepArrayList())
                    if (entity.getUniqueId().equals(event.getEntity().getUniqueId()))
                        event.setCancelled(true);
        });
    }
}
