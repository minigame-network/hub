package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.collectibles.CollectibleVisibility;
import me.chasertw123.minigames.core.event.UserToggleSettingEvent;
import me.chasertw123.minigames.core.user.data.settings.Setting;
import me.chasertw123.minigames.hub.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Event_PlayerToggleSetting implements Listener {

    @EventHandler
    public void onPlayerToggleSetting(UserToggleSettingEvent event) {

        if (event.getSetting() == Setting.PLAYER_VISIBILITY)
            Main.getUserManager().toCollection().stream()
                    .filter(user -> !user.getUuid().equals(event.getUser().getUUID()))
                    .forEach(user -> user.getUnlockedCollectibles().stream()
                            .filter(CollectibleVisibility.class::isInstance)
                            .forEach(c -> {
                                if (event.getValue())
                                    ((CollectibleVisibility) c).showCollectible(event.getUser());

                                else
                                    ((CollectibleVisibility) c).hideCollectible(event.getUser());
                            }));
    }
}
