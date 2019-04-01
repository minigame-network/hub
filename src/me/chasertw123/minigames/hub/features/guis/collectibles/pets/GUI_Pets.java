package me.chasertw123.minigames.hub.features.guis.collectibles.pets;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.collectibles.GUI_Collectibles;
import me.chasertw123.minigames.hub.user.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

/**
 * Created by Chase on 1/14/2018.
 */
public class GUI_Pets extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 39, 40, 41, 44, 45, 46, 47, 48, 50, 51, 52, 53};

    public GUI_Pets(User user) {
        super(6, "Pets", user.getCoreUser());

        for (int slot : BORDER_FILLER_SLOTS)
            setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4, " "), slot, (s, c, p) -> {});

        setItem(new cItemStack(Material.REDSTONE_COMPARATOR, ChatColor.RED + "" + ChatColor.BOLD + "Back to Arcade Prizes"), 49, (s, c, p) -> new GUI_Collectibles(user));
    }

}
