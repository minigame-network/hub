package me.chasertw123.minigames.hub.features.guis.collectibles.joinmessages;

import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.shared.utils.StringUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Chase on 1/15/2018.
 */
public enum LoginMessage {

    // TODO: Add more messages

    WALKED_IN("just walked into the arcade! (WIP)"),
    GO_KARTING("finished first while Go-Karting! (WIP)"),
    LASER_TAG("killed everyone in a game of laser tag! (WIP)");

    private String message;

    LoginMessage(String message) {
        this.message = message;
    }

    public ItemStack createItemStack(User pp) {

        cItemStack item = new cItemStack(Material.PAPER);

        item.setDisplayName(ChatColor.GOLD + StringUtil.niceString(this.toString().replaceAll("_", " ")));

        // TODO: Create nicely formatted lore

        return item;
    }

    public String createMessage(User pp) {

        ChatColor nameColor = pp.getRank().getRankColor();
        if (!pp.getRank().isStaff() && pp.isDeluxe())
            nameColor = ChatColor.GOLD;

        return nameColor + pp.getUsername() + ChatColor.GOLD + " " + this.message;
    }
}
