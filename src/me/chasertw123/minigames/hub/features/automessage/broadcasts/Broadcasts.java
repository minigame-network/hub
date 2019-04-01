package me.chasertw123.minigames.hub.features.automessage.broadcasts;

import me.chasertw123.minigames.core.user.User;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 7/29/2017.
 */
public enum Broadcasts {

    CHECK_OUT_FORUMS(null);

    private Broadcast broadcastClass = null;

    Broadcasts(Broadcast broadcastClass) {
        this.broadcastClass = broadcastClass;
    }

    public void send(User pp) {
        pp.getPlayer().spigot().sendMessage(broadcastClass.buildMessage());
    }

    public void send(Player player) {
        player.spigot().sendMessage(broadcastClass.buildMessage());
    }
}
