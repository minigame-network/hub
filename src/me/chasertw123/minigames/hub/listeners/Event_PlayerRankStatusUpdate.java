package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.event.UserRankStatusUpdateEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Chase on 1/13/2018.
 */
public class Event_PlayerRankStatusUpdate implements Listener {

    @EventHandler
    public void onPlayerRankStatusUpdate(UserRankStatusUpdateEvent event) {

        ChatColor tablistColor = event.getUser().getRank().getRankColor();
        if (!event.getUser().getRank().isStaff() && event.getUser().isDeluxe())
            tablistColor = ChatColor.GOLD;

        event.getUser().getPlayer().setPlayerListName(tablistColor + event.getUser().getPlayer().getName());
    }
}
