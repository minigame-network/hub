package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.event.BoosterUpdateEvent;
import me.chasertw123.minigames.hub.features.guis.GUI_ServerSelector;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Chase on 1/14/2018.
 */
public class Event_BoosterUpdateEvent implements Listener {

    @EventHandler
    public void onBoosterUpdate(BoosterUpdateEvent event) {
        CoreAPI.getOnlinePlayers().stream()
                .filter(pp -> pp.getCurrentGui() instanceof GUI_ServerSelector)
                .forEach(GUI_ServerSelector::new);
    }
}
