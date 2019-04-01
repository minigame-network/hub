package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.collectibles.gadgets.GadgetCollectible;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class Event_PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(me.chasertw123.minigames.core.event.UserQuitEvent event) {

        User user = Main.getUserManager().get(event.getUser().getUUID());
        if (!user.save())
            Bukkit.getLogger().severe("Failed to save " + user.getPlayer().getName() + "'s data!");

        else
            System.out.println("Saved " + user.getPlayer().getName() + "'s Data!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {

        event.setQuitMessage(null);

        User user = Main.getUserManager().get(event.getPlayer().getUniqueId());

        user.getUnlockedCollectibles()
                .stream()
                .filter(GadgetCollectible.class::isInstance)
                .map(GadgetCollectible.class::cast)
                .forEach(GadgetCollectible::onClear);

        if (user.getActiveMorph() != null)
            user.disableMorph();

        if (user.getActiveParticle() != null)
            user.disableParticle();

        Main.getNPCManager().getNpcs().keySet().stream()
                .filter(npc -> Main.getNPCManager().getNpcs().get(npc).getPlayersAroundNpc().contains(user.getUuid()))
                .forEach(npc -> Main.getNPCManager().getNpcs().get(npc).getPlayersAroundNpc().remove(user.getUuid()));


        Main.getUserManager().remove(event.getPlayer().getUniqueId());
    }
}
