package me.chasertw123.minigames.hub.features.guis.profile;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * Created by Chase on 8/5/2017.
 */
public class GUI_GameBoosters extends AbstractGui {

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 5, 6, 7, 8, 36, 37, 38, 39, 41, 42, 43, 44};

    public GUI_GameBoosters(User user) {
        super(5, "Network Boosters", user.getCoreUser());
        me.chasertw123.minigames.core.user.User pp = user.getCoreUser();

        setItem(new cItemStack(Material.WATER_LILY, ChatColor.AQUA + "" + ChatColor.BOLD + "Water" + ChatColor.BLUE + "" + ChatColor.BOLD + "Wars", ChatColor.GRAY + "Gives " + ChatColor.AQUA
                + "EVERYONE " + ChatColor.GRAY + "a bonus coin multiplier in", ChatColor.GRAY + "water wars solo or team mode!", "", ChatColor.GRAY + "Your Water Wars Boosters: " + ChatColor.RED
                + pp.getBoosters().get(ServerGameType.WATER_WARS), "", ChatColor.YELLOW + "Click to boost Water Wars!"), 20, (s, c, p) -> pp.getPlayer().performCommand("gamebooster "
                + ServerGameType.WATER_WARS.toString()));

        for (int slot : FILLER_SLOTS)
            setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {});

        setItem(pp.toItemStack(), 4, (s, c, p) -> {});
        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Return to profile"), 40, (s, c, p) -> new GUI_Profile(user));
    }
}
